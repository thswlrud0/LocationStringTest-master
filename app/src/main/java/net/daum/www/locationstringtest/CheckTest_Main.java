package net.daum.www.locationstringtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CheckTest_Main extends AppCompatActivity {
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb8;
    private CheckBox cb9;
    private CheckBox cb10;
    private CheckBox cb11;
    private CheckBox cb12;
    private CheckBox cb13;
    private CheckBox cb14;
    private CheckBox cb15;
    private CheckBox cb16;
    private CheckBox cb17;
    private CheckBox cb18;

    private Button button;
    private Button button1;

    int vnum1,vnum2,vnum3,vnum4,vnum5;
    int anum1,anum2,anum3,anum4,anum5;
    int snum1,snum2,snum3,snum4,snum5;
    int tnum1,tnum2,tnum3,tnum4,tnum5;


    private DatabaseReference Ref1 = FirebaseDatabase.getInstance().getReference();

    private DatabaseReference A1 =Ref1.child("App").child("안함");
    private DatabaseReference A2 =Ref1.child("App").child("가끔");
    private DatabaseReference A3 =Ref1.child("App").child("보통");
    private DatabaseReference A4 =Ref1.child("App").child("자주");
    private DatabaseReference A5 =Ref1.child("App").child("매번");

    private DatabaseReference V1 = Ref1.child("Vehicle").child("버스");
    private DatabaseReference V2 = Ref1.child("Vehicle").child("지하철");
    private DatabaseReference V3 = Ref1.child("Vehicle").child("택시");
    private DatabaseReference V4 = Ref1.child("Vehicle").child("자가용");
    private DatabaseReference V5 = Ref1.child("Vehicle").child("기타");

    private DatabaseReference S1 = Ref1.child("Sum").child("0~3");
    private DatabaseReference S2 = Ref1.child("Sum").child("3~7");
    private DatabaseReference S3 = Ref1.child("Sum").child("7~10");
    private DatabaseReference S4 = Ref1.child("Sum").child("10번이상");

    private DatabaseReference T1 = Ref1.child("Topic").child("맛집");
    private DatabaseReference T2 = Ref1.child("Topic").child("견학");
    private DatabaseReference T3 = Ref1.child("Topic").child("데이트");
    private DatabaseReference T4 = Ref1.child("Topic").child("사진");

    private LinearLayout mLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_test__main);

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);
        cb6 = (CheckBox) findViewById(R.id.checkBox6);
        cb7 = (CheckBox) findViewById(R.id.checkBox7);
        cb8 = (CheckBox) findViewById(R.id.checkBox8);
        cb9 = (CheckBox) findViewById(R.id.checkBox9);
        cb10 = (CheckBox) findViewById(R.id.checkBox10);
        cb11 = (CheckBox) findViewById(R.id.checkBox11);
        cb12 = (CheckBox) findViewById(R.id.checkBox12);
        cb13 = (CheckBox) findViewById(R.id.checkBox13);
        cb14 = (CheckBox) findViewById(R.id.checkBox14);
        cb15 = (CheckBox) findViewById(R.id.checkBox15);
        cb16 = (CheckBox) findViewById(R.id.checkBox16);
        cb17 = (CheckBox) findViewById(R.id.checkBox17);
        cb18 = (CheckBox) findViewById(R.id.checkBox18);


        button = (Button) findViewById(R.id.button1);
        button1 = (Button) findViewById(R.id.button2);

    }

    @Override
    protected void onStart() {
        super.onStart();

        A1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int a1=dataSnapshot.getValue(int.class);
                anum1=a1;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        A2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int a2=dataSnapshot.getValue(int.class);
                anum2=a2;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        A3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int a3=dataSnapshot.getValue(int.class);
                anum3=a3;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        A4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int a4=dataSnapshot.getValue(int.class);
                anum4=a4;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        A5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int a5=dataSnapshot.getValue(int.class);
                anum5=a5;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        V1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int v1=dataSnapshot.getValue(int.class);
                vnum1=v1;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        V2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int v2=dataSnapshot.getValue(int.class);
                vnum2=v2;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        V3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int v3=dataSnapshot.getValue(int.class);
                vnum3=v3;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        V4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int v4=dataSnapshot.getValue(int.class);
                vnum4=v4;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        V5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int v5=dataSnapshot.getValue(int.class);
                vnum5=v5;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        S1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int s1=dataSnapshot.getValue(int.class);
                snum1=s1;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        S2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int s2=dataSnapshot.getValue(int.class);
                snum2=s2;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        S3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int s3=dataSnapshot.getValue(int.class);
                snum3=s3;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        S4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int s4=dataSnapshot.getValue(int.class);
                snum4=s4;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        T1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int t1=dataSnapshot.getValue(int.class);
                tnum1=t1;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        T2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int t2=dataSnapshot.getValue(int.class);
                tnum2=t2;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        T3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int t3=dataSnapshot.getValue(int.class);
                tnum3=t3;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        T4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int t4=dataSnapshot.getValue(int.class);
                tnum4=t4;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cb1.isChecked()==true) {
                    A1.setValue(anum1++);
                }
                else if(cb2.isChecked()==true) {
                    A2.setValue(anum2++);
                }
                else if(cb3.isChecked()==true) {
                    A3.setValue(anum3++);
                }
                else if(cb4.isChecked()==true) {
                    A4.setValue(anum4++);
                }
                else if(cb5.isChecked()==true) {
                    A5.setValue(anum5++);
                }
                else{}

                if (cb6.isChecked()==true) {
                    V1.setValue(vnum1++);
                } else if (cb7.isChecked()==true) {
                    V2.setValue(vnum2++);
                } else if (cb8.isChecked()==true) {
                    V3.setValue(vnum3++);
                } else if (cb9.isChecked()==true) {
                    V4.setValue(vnum4++);
                } else if (cb10.isChecked()==true) {
                    V5.setValue(vnum5++);
                }
                else {}

                if (cb11.isChecked()==true) {
                    S1.setValue(snum1++);
                } else if (cb12.isChecked()==true) {
                    S2.setValue(snum2++);
                } else if (cb13.isChecked()==true) {
                    S3.setValue(snum3++);
                } else if (cb14.isChecked()==true) {
                    S4.setValue(snum4++);
                }
                else{}

                if (cb15.isChecked()==true) {
                    T1.setValue(tnum1++);
                } else if (cb16.isChecked()==true) {
                    T2.setValue(tnum2++);
                } else if (cb17.isChecked()==true) {
                    T3.setValue(tnum3++);
                } else if (cb18.isChecked()==true) {
                    T4.setValue(tnum4++);
                }
                else{}
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = imageView.newIntent(CheckTest_Main.this);
                startActivity(intent);
                if(cb1.isChecked()==true) {
                    A1.setValue(anum1++);
                }
                else if(cb2.isChecked()==true) {
                    A2.setValue(anum2++);
                }
                else if(cb3.isChecked()==true) {
                    A3.setValue(anum3++);
                }
                else if(cb4.isChecked()==true) {
                    A4.setValue(anum4++);
                }
                else if(cb5.isChecked()==true) {
                    A5.setValue(anum5++);
                }
                else{}

                if (cb6.isChecked()==true) {
                    V1.setValue(vnum1++);
                } else if (cb7.isChecked()==true) {
                    V2.setValue(vnum2++);
                } else if (cb8.isChecked()==true) {
                    V3.setValue(vnum3++);
                } else if (cb9.isChecked()==true) {
                    V4.setValue(vnum4++);
                } else if (cb10.isChecked()==true) {
                    V5.setValue(vnum5++);
                }
                else {}

                if (cb11.isChecked()==true) {
                    S1.setValue(snum1++);
                } else if (cb12.isChecked()==true) {
                    S2.setValue(snum2++);
                } else if (cb13.isChecked()==true) {
                    S3.setValue(snum3++);
                } else if (cb14.isChecked()==true) {
                    S4.setValue(snum4++);
                }
                else{}

                if (cb15.isChecked()==true) {
                    T1.setValue(tnum1++);
                } else if (cb16.isChecked()==true) {
                    T2.setValue(tnum2++);
                } else if (cb17.isChecked()==true) {
                    T3.setValue(tnum3++);
                } else if (cb18.isChecked()==true) {
                    T4.setValue(tnum4++);
                }
                else{}
            }
        });
    }
}
