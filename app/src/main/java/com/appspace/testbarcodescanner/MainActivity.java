package com.appspace.testbarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnScanBarcode;
    private TextView tvBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
    }

    private void initInstances() {
        btnScanBarcode = (Button) findViewById(R.id.btnScanBarcode);
        assert btnScanBarcode != null;
        btnScanBarcode.setOnClickListener(this);

        tvBarcode = (TextView) findViewById(R.id.tvBarcode);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnScanBarcode:
                scanBarcode();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                tvBarcode.setText("");
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                tvBarcode.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void scanBarcode() {
        new IntentIntegrator(this).initiateScan();
    }
}
