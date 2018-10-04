package cn.bmob.sdkdemo.bean;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {

	private static final long serialVersionUID = 1L;
	private Integer age;
	private Integer num;
	private Boolean sex;

	private List<String> hobby;		// 对应服务端Array类型：String类型的集合
	private List<BankCard> cards;	// 对应服务端Array类型:Object类型的集合

	private BankCard mainCard;      //主卡
	private Person banker;          //银行工作人员
	private MyUser friend;

	private List<String> imies;

	public List<String> getImies() {
		return imies;
	}

	public void setImies(List<String> imies) {
		this.imies = imies;
	}

	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public List<BankCard> getCards() {
		return cards;
	}
	public void setCards(List<BankCard> cards) {
		this.cards = cards;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	public BankCard getMainCard() {
		return mainCard;
	}
	public void setMainCard(BankCard mainCard) {
		this.mainCard = mainCard;
	}
	public Person getBanker() {
		return banker;
	}
	public void setBanker(Person banker) {
		this.banker = banker;
	}

	public MyUser getFriend() {
		return friend;
	}

	public void setFriend(MyUser friend) {
		this.friend = friend;
	}

	@Override
	public String toString() {
		return "MyUser{" +
				"age=" + getAge() +
				"name=" + getUsername() +
				", num=" + num +
				", sex=" + sex +
				", hobby=" + hobby +
				", cards=" + cards +
				", mainCard=" + mainCard +
				", banker=" + banker +
				'}';
	}
}
