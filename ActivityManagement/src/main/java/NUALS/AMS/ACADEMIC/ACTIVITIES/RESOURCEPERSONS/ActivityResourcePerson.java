package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;

import javax.persistence.EmbeddedId;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)

public class ActivityResourcePerson {

	@EmbeddedId
	private ActivityResourcePersonKey actRePerKey;

	public ActivityResourcePersonKey getActRePerKey() {
		return actRePerKey;
	}

	public void setActRePerKey(ActivityResourcePersonKey actRePerKey) {
		this.actRePerKey = actRePerKey;
	}
	
}
