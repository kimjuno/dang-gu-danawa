package DangGuDANAWA.Object3D.Utils;

import DangGuDANAWA.Activity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpTextSimulation extends Activity implements View.OnClickListener{

	public TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.simulation_help_text);		
		
		Button OKButton = (Button) findViewById(R.id.button_simulation_ok);
		OKButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub		
		finish();		
	}
}
