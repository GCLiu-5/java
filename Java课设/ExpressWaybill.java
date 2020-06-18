package classend;

public class ExpressWaybill {
	private String date;             //��������
	private String consignerName;    //����������
	private String consignerTel;     //�����˵绰
	private String consignerAddress; //�����˵�ַ
	private String recipientName;    //�ռ�������
	private String recipientTel;     //�ռ��˵绰
	private String recipientAddress; //�ռ��˵�ַ
	private String orderNum;         //������

	public ExpressWaybill(String date, String consignerName, String consignerTel, String consignerAddress, String recipientName, String recipientTel, String recipientAddress) {
		setDate(date);
		setConsignerName(consignerName);
		setConsignerTel(consignerTel);
		setConsignerAddress(consignerAddress);
		setRecipientName(recipientName);
		setRecipientTel(recipientTel);
		setRecipientAddress(recipientAddress);
		this.orderNum = null;
	}
	public String toString() {
		return "ExpressWaybill [date=" + date + ",\n consignerName=" + consignerName + ",\n consignerTel=" + consignerTel
				+ ",\n consignerAddress=" + consignerAddress + ",\n recipientName=" + recipientName + ",\n recipientTel="
				+ recipientTel + ",\n recipientAddress=" + recipientAddress + ",\n orderNum=" + orderNum + "]\n";
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getConsignerName() {
		return consignerName;
	}
	public void setConsignerName(String consignerName) {
		this.consignerName = consignerName;
	}
	public String getConsignerTel() {
		return consignerTel;
	}
	public void setConsignerTel(String consignerTel) {
		this.consignerTel = consignerTel;
	}
	public String getConsignerAddress() {
		return consignerAddress;
	}
	public void setConsignerAddress(String consignerAddress) {
		this.consignerAddress = consignerAddress;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientTel() {
		return recipientTel;
	}
	public void setRecipientTel(String recipientTel) {
		this.recipientTel = recipientTel;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
