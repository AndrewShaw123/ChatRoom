package listener;

import view.ServiceHistoryView;
import util.IOUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action=e.getActionCommand();
		if(action.equals("停止")) {
			stop();
		}else if(action.equals("日志消息")){
			history();
		}
		
	}
	
	private void stop() {
		System.exit(0);
	}

	private void history(){
		ServiceHistoryView.serviceHistoryView();
		String history=IOUtil.readHistory("service");
		ServiceHistoryView.historyArea.setText(history);
	}

}
