package com.example.calculator;

import android.content.Context;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onViewClicked();
        configEditText();
    }

    private void configEditText() {
        binding.etInput.setInputType(InputType.TYPE_NULL);
    }

    public void onViewClicked() {

        View[] buttons = new View[] {binding.btnOne, binding.btnTwo, binding.btnThree, binding.btnFour,
                binding.btnFive, binding.btnSix, binding.btnSeven, binding.btnEight, binding.btnNine, binding.btnZero};

        for(View button : buttons) {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.etInput.append(((MaterialButton)view).getText().toString());
                }
            });
        }

        View[] buttonsOperators = new View[] {binding.btnAdd, binding.btnSub, binding.btnSplit, binding.btnMult};

        for(View button : buttonsOperators) {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resolve(false);
                    String operator = ((MaterialButton)view).getText().toString();
                    String operation = binding.etInput.getText().toString();
                    String lastCharacter =
                            operation.isEmpty() ? "" : operation.substring(operation.length() - 1);

                    if(operator.equals(Consts.OPERATOR_SUB)) {
                        if(operation.isEmpty() ||
                                (!(lastCharacter.equals(Consts.OPERATOR_SUB)) &&
                                        !(lastCharacter.equals(Consts.DOT)))) {
                            binding.etInput.append(operator);
                        }
                    } else {
                        if(!operation.isEmpty() &&
                                !(lastCharacter.equals(Consts.OPERATOR_SUB)) && !(lastCharacter.equals(Consts.DOT))) {
                            binding.etInput.append(operator);
                        }
                    }
                }
            });
        }

        binding.btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String operation = binding.etInput.getText().toString();
                String operator = Methods.getOperator(operation);
                int count = operation.length() - operation.replace(".", "").length();

                if(!operation.contains(Consts.DOT) || count < 2 && (!operator.equals(Consts.OPERATOR_NULL))) {

                    binding.etInput.append(binding.btnDot.getText());
                }
            }
        });

        binding.btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etInput.setText("");
            }
        });

        binding.btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resolve(true);
            }
        });
    }

    private void resolve(boolean fromResult) {

    }
}