public class sdf {
    private void makeBox() {
		for (i = 0; i < 3; i++) {
//			imgLabel[i] = new JLabel();
//			imgLabel[i].setIcon(updateimage);
//			imgLabel[i].setHorizontalAlignment(JLabel.CENTER);
//			imgLabel2[i] = new JLabel();
//			imgLabel2[i].setIcon(updateimage2);
//			imgLabel2[i].setHorizontalAlignment(JLabel.CENTER);
			info[i] = new JPanel();
			box[i] = new JPanel();
			buy[i] = new JButton();
			teamImgPanel[i] = new JPanel();

			mGameLabels.add(new JLabel(mConcert));
			mDateLabels.add(new JLabel(mDate.get(i)));
			mTimeLabels.add(new JLabel(mTime));

			info[i].setLayout(new GridLayout(3, 1));
			info[i].add(mGameLabels.get(i));
			info[i].add(mDateLabels.get(i));
			info[i].add(mTimeLabels.get(i));
			info[i].setBackground(Color.WHITE);

			buy[i].setText("예매하기");

			teamImgPanel[i].setLayout(new GridLayout(1, 3));
			teamImgPanel[i].add(imgLabel[i]);
			teamImgPanel[i].add(new JLabel("                  VS"));
			teamImgPanel[i].add(imgLabel2[i]);
			teamImgPanel[i].setBackground(Color.WHITE);

			box[i].setLayout(new BorderLayout(20, 10));
			box[i].add(info[i], BorderLayout.CENTER);
			box[i].add(teamImgPanel[i], BorderLayout.WEST);
			box[i].add(buy[i], BorderLayout.EAST);
			box[i].setBackground(Color.WHITE);
		}
	}
}
