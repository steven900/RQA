package wx.weixin.bean;

public class ComplexButton extends Buttonw {
	
	private Buttonw[] sub_button;

	public ComplexButton(String name, Buttonw[] sub_button) {
		super(name);
		this.sub_button = sub_button;
	}

	public Buttonw[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Buttonw[] sub_button) {
		this.sub_button = sub_button;
	}

}
