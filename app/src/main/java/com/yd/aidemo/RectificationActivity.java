package com.yd.aidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RectificationActivity extends AppCompatActivity {

    private ImageView back;
    private ResultBean mResultBean;
    private TextView problem;
    private TextView location;
    private TextView responsibleUnit;
    private TextView responsible;

    private String locationText;
    private String problemText;
    private String responsibleUnitText;
    private String responsibleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectification);

        initData();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        problem = findViewById(R.id.problem);
        location = findViewById(R.id.location);
        responsible = findViewById(R.id.responsible);
        responsibleUnit = findViewById(R.id.responsible_unit);

        problem.setText(problemText);
        location.setText(locationText);
        responsible.setText(responsibleText);
        responsibleUnit.setText(responsibleUnitText);
    }


    private void initData(){
        mResultBean = (ResultBean) getIntent().getSerializableExtra("data");
        locationText = mResultBean.getProblemPosition().get(0);
        double factor = -1;
        for (ProblemBean problemBean : mResultBean.getProblem()) {
            if (factor <= problemBean.getMatch()){
                factor = problemBean.getMatch();
                problemText = problemBean.getName();
            }
        }

        responsibleUnitText = mResultBean.getSubcontractingUnit().get(0).getName();
        responsibleText = mResultBean.getProjectPersonnel().get(0).getName();
    }
}
