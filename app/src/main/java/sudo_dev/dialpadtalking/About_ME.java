package sudo_dev.dialpadtalking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

@SuppressLint("Registered")
public class About_ME extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_about__me );
       final Button num1=findViewById ( R.id.num1 );
       // final Button num2=findViewById ( R.id.num2 );

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData( Uri.fromParts ("mailto","sudodeveloper81@gmail.com",null));
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
