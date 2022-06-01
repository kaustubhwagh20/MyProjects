package com.kaustubh.connect_x1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothServerSocket;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
/* Steps
a) Show Video
b) Login ----> aa)Signin: get pref and Check --->True---->Connecting activitty
*              bb) Signup: Signup Screen : Reset--> Clear Screen--->Submit : Add details to pref.---> Login screen
c) Connecting: a) show paired devices   abt)BT Turnon      bbt) BT Turn Off cbt) Scan for new dbt) connect to module & Driving activity
d) Diving activity : abt) Send socket to module btv) Read Received data.
*/
public class MainActivity2<bluetoothIn> extends AppCompatActivity {
    BluetoothDevice dev;
    BluetoothSocket sock;
    String[] permit = {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    int per = 0;
    BluetoothAdapter btAdapter;
    public static int REQUEST_ENABLE_BT = 10;
    ArrayList<BluetoothDevice> btsc, btpr;
    IntentFilter filter = new IntentFilter();
    String compname = "";
    BroadcastReceiver mReceiver;
    ArrayList<String> scnres;
    Thread msend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            showMessage("device not supported");
        }
        GetPermit(per);
    }

    protected void GetPermit(int p) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (p < permit.length) {
                if (ContextCompat.checkSelfPermission(this, permit[p]) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            permit[p])) {
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{permit[p]},
                                123);
                    }
                } else {
                    per += 1;
                    GetPermit(per);
                }
            } else {
                setContentView(R.layout.activity_main); /// Show Login Screen
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    per += 1;
                    GetPermit(per);
                } else {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    builder.setMessage(permissions[0] + " is required to run Application");
                    builder.setTitle("Please grant this permission");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            GetPermit(per);
                        }
                    });
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    });
                    android.app.AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
            break;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                String name = (etr(R.id.et_user).getText().toString() + "^" + etr(R.id.et_pass).getText().toString());
                String comp = prefs.getString("Userdt", "");
                if (name.equals(comp)) {
                    btpr = new ArrayList<BluetoothDevice>();
                    conectingact();
                } else {
                    etr(R.id.et_user).setText("");
                    etr(R.id.et_pass).setText("");
                    showMessage("User Not Found Try Again with correct passwod or Signup for create account");
                }
                break;
            case R.id.bt_sup:
                setContentView(R.layout.activity_sign);
                break;
            case R.id.bt_reset:
                setContentView(R.layout.activity_sign);
                break;
            case R.id.bt_submit:
                if (etr(R.id.et_npass).getText().toString().equals(etr(R.id.et_cnpass).getText().toString())) {
                    String nname = (etr(R.id.et_uname).getText().toString() + "^" + etr(R.id.et_npass).getText().toString());
                    SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
                    prefEditor.putString("Userdt", nname);
                    prefEditor.apply();
                    setContentView(R.layout.activity_main);
                } else {
                    showMessage("Password and Confirm Password not Match");
                }
                break;
            case R.id.bt_ton:
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                break;
            case R.id.bt_toff:
                btAdapter.disable();
            case R.id.ff:
                showMessage("Forwording");
                new ConnectedThread().write("F".getBytes());//send("F");//sock.getOutputStream().write(data.getBytes());
                break;
            case R.id.bb:
                showMessage("Reversing");
                new ConnectedThread().write("B".getBytes());
                break;
            case R.id.ll:
                showMessage("Turning Left");
                new ConnectedThread().write("L".getBytes());
                break;
            case R.id.rr:
                showMessage("Turning Right");
                new ConnectedThread().write("R".getBytes());
                break;
            case R.id.br:
                showMessage("Uffffff... Stopppppp");
                new ConnectedThread().write("S".getBytes());
                break;
            case R.id.bt_scan:
                scnres = new ArrayList<String>();
                btsc = new ArrayList<BluetoothDevice>();
                Scanned();
                break;
            case R.id.bt_back:
                new ConnectedThread().cancel();
                break;
            case R.id.bt_logout:
                unregisterReceiver(mReceiver);
                exitApp();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                showMessage("Bluetooth enabled");
                conectingact();
            } else {
                showMessage("There is an error to enable bluetooth adapter");
            }
        }
    }

    public void conectingact() {
        setContentView(R.layout.activity_connect);
        if (btAdapter.isEnabled()) {
            ArrayList<String> deviceStrs = new ArrayList<String>();
            final ArrayList<String> devices = new ArrayList<String>();
            Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    btpr.add(device);
                    deviceStrs.add(device.getName() + "\n" + device.getAddress());
                    devices.add(device.getAddress());
                }
            }
            ArrayAdapter<String> pairadpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, deviceStrs);
            ListView ltp = (ListView) findViewById(R.id.lt_paired);
            ltp.setAdapter(pairadpt);
            ltp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BluetoothDevice cacvt = null;
                    for (BluetoothDevice device : btpr) {
                        if (((String) parent.getItemAtPosition(position)).contains(device.getAddress())) {
                            cacvt = device;
                            break;
                        }
                    }
                    new ConnectThread(cacvt).start();
                }
            });
        } else {
            showMessage("Blueooth is not enabled yet, Click on turn on bluetooth");
        }

    }

    public void Scanned() {
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
            Scanned();
        }
        compname = "";
        scnres.add("Null:\n00:00:00:00:00");
        ArrayAdapter<String> scndadpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, scnres);
        ListView scl = (ListView) findViewById(R.id.lt_scandev);
        scl.setAdapter(scndadpt);
        scl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pairDevice((String) parent.getItemAtPosition(position));//,btsc);
            }
        });
        btAdapter.startDiscovery();
        mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device.getName() != null) {
                        if (!compname.contains(device.getAddress())) {
                            compname += "^" + device.getAddress();
                            btsc.add(device);
                            scnres.add(device.getName() + "\n" + device.getAddress());
                            scndadpt.add(device.getName() + "\n" + device.getAddress());
                            scndadpt.remove("Null:\n00:00:00:00:00");
                            scnres.remove("Null:\n00:00:00:00:00");
                            scndadpt.setNotifyOnChange(true);
                        }
                    }
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    btAdapter.cancelDiscovery();
                    compname = "";
                    //showMessage(""+scnres.size());
                }
            }
        };
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    private void pairDevice(String devid) {//BluetoothDevice device) {
        BluetoothDevice dev = null;
        for (BluetoothDevice bt : btsc) {
            if (devid.contains(bt.getAddress())) {
                dev = bt;
                break;
            }
        }
        try {
            Method method = dev.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(dev, (Object[]) null);
            new ConnectThread(dev).start();
        } catch (Exception e) {
            showMessage(e.toString());
        }
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(final String toast) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public EditText etr(int id) {
        return (EditText) findViewById(id);
    }

    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private class ConnectThread extends Thread {
        public ConnectThread(BluetoothDevice device) {
            setContentView(R.layout.activity_driving1);
            ParcelUuid[] uuids = device.getUuids();
            dev = device;
            try {
                sock = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
            } catch (IOException e) {
            }
        }

        public void run() {
            try {
                sock.connect();
                for(int i=0;i<10000;++i){}
                if (isConnected(dev)) {
                    showMessage(dev.getName() + " is Connected");
                } else {
                    showMessage("There is an error to connect " + dev.getName() + "Try again");
                    try {
                        sock.close();
                    } catch (IOException e) {}
                    //setContentView(R.layout.activity_connect);
                }
            } catch (IOException connectException) {
                showMessage(connectException.toString());
                try {
                    sock.close();
                } catch (IOException closeException) {
                    showMessage(closeException.toString());
                }
                return;
            }
        }

        public void cancel() {
            try {
                sock.close();
                setContentView(R.layout.activity_connect);
            } catch (IOException e) {
                showMessage(e.toString());
            }
        }
    }

    public static boolean isConnected(BluetoothDevice device) {
        try {
            Method m = device.getClass().getMethod("isConnected", (Class[]) null);
            boolean connected = (boolean) m.invoke(device, (Object[]) null);
            return connected;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread() {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = sock.getInputStream();
                tmpOut = sock.getOutputStream();
            } catch (IOException e) {
                showMessage(e.toString());
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

       @Override
       public void run() {
           byte[] buffer = new byte[1024];
           int bytes;

           while (true) {
               try {
                   bytes = mmInStream.read(buffer);
                   String strReceived = new String(buffer, 0, bytes);
                   final String msgReceived = String.valueOf(bytes) +
                           " bytes received:\n"
                           + strReceived;

                   runOnUiThread(new Runnable(){

                       @Override
                       public void run() {
                          showMessage(msgReceived);
                       }});
               } catch (IOException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   final String msgConnectionLost = "Connection lost:\n"
                           + e.getMessage();
                   runOnUiThread(new Runnable(){
                     @Override
                       public void run() {
                           showMessage(msgConnectionLost);
                       }});
               }
           }
       }
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                showMessage(e.toString());
            }
        }

        public void cancel() {
            try {
                sock.close();
                setContentView(R.layout.activity_connect);
            } catch (IOException e) {
            }
        }
    }

  /*  String rvddata = "";
    final int handlerState = 0;
    private Handler bluetoothIn = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == handlerState) {                                     //if message is what we want
                String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                rvddata += readMessage;                                      //keep appending to string until ~
                showMessage(rvddata);
            }
        }
    };*/
}
 /*          private Handler mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    byte[] writeBuf = (byte[]) msg.obj;
                    int begin = (int) msg.arg1;
                    int end = (int) msg.arg2;
                    switch (msg.what) {
                        case 1:
                            String writeMessage = new String(writeBuf);
                            writeMessage = writeMessage.substring(begin, end);
                            showMessage(writeMessage);
                            break;
                    }
                }
            };

}*/
