package NUALS.AMS.ACADEMIC.ADVANCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class GoodsReturned {
	

    @Id
    @Column(name = "goodsReturnId",nullable = false,  length=50)	
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int goodsReturnId;
    

    @NotNull
	@ManyToOne
	@JoinColumn(name="setId")
    private AdvExpSettlement ac;
    
    
    @Column(name = "itemName", nullable = true, length=500)
    private String item;
    
    private int qty;
    
    private double value;
    
    private String enteredBy;
    
    private java.util.Date EnteredDate; 
    
    public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public java.util.Date getEnteredDate() {
		return EnteredDate;
	}

	public void setEnteredDate(java.util.Date enteredDate) {
		EnteredDate = enteredDate;
	}

	@Column(name = "goodsRemarks", nullable = true, length=1000)
    private String goodsRemarks;
    
    
    

	public int getGoodsReturnId() {
		return goodsReturnId;
	}

	public void setGoodsReturnId(int goodsReturnId) {
		this.goodsReturnId = goodsReturnId;
	}

	public AdvExpSettlement getAc() {
		return ac;
	}

	public void setAc(AdvExpSettlement ac) {
		this.ac = ac;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getGoodsRemarks() {
		return goodsRemarks;
	}

	public void setGoodsRemarks(String goodsRemarks) {
		this.goodsRemarks = goodsRemarks;
	}

	@Override
	public String toString() {
		return "GoodsReturned [goodsReturnId=" + goodsReturnId + ", ac=" + ac + ", item=" + item + ", qty=" + qty
				+ ", value=" + value + ", enteredBy=" + enteredBy + ", EnteredDate=" + EnteredDate + ", goodsRemarks="
				+ goodsRemarks + "]";
	}


    
    

}
