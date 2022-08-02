package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int courseId;
	
	@NotBlank
	@Column(nullable = false,unique = true)
	private String courseCode;
	
	@NotBlank
	@Column(nullable = false,unique = true)
	private String courseTitle;
	
	private Float courseDuration;
	
	private String courseDurationUnit;
	@NotBlank
	private String courseExamCategory;
	private String courseStatus;
	private Date courseStartDate;
	private int courseCompletedBatches;
	private int courseContactStaffId;
	private String 	courseProspectusUrl;
	private int courseNoOfSems;
	private String courseEnteredBy;
	private String courseVerifiedBy;
	private Date courseEnteredOn;
	private Date courseVerifiedOn;
	private String courseState;//open,stopped,removed etc
	
	public Course(int courseId, @NotBlank String courseCode, @NotBlank String courseTitle, Float courseDuration,
			String courseDurationUnit, @NotBlank String courseExamCategory, String courseStatus, Date courseStartDate,
			int courseCompletedBatches, int courseContactStaffId, String courseProspectusUrl, int courseNoOfSems,
			String courseEnteredBy, String courseVerifiedBy, Date courseEnteredOn, Date courseVerifiedOn,
			String courseState) {
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.courseDuration = courseDuration;
		this.courseDurationUnit = courseDurationUnit;
		this.courseExamCategory = courseExamCategory;
		this.courseStatus = courseStatus;
		this.courseStartDate = courseStartDate;
		this.courseCompletedBatches = courseCompletedBatches;
		this.courseContactStaffId = courseContactStaffId;
		this.courseProspectusUrl = courseProspectusUrl;
		this.courseNoOfSems = courseNoOfSems;
		this.courseEnteredBy = courseEnteredBy;
		this.courseVerifiedBy = courseVerifiedBy;
		this.courseEnteredOn = courseEnteredOn;
		this.courseVerifiedOn = courseVerifiedOn;
		this.courseState = courseState;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}
	public Course(int courseId, @NotBlank String courseCode, @NotBlank String courseTitle, Float courseDuration,
			String courseDurationUnit, @NotBlank String courseExamCategory, String courseStatus, Date courseStartDate,
			int courseCompletedBatches, int courseContactStaffId, String courseProspectusUrl, int courseNoOfSems) {
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.courseDuration = courseDuration;
		this.courseDurationUnit = courseDurationUnit;
		this.courseExamCategory = courseExamCategory;
		this.courseStatus = courseStatus;
		this.courseStartDate = courseStartDate;
		this.courseCompletedBatches = courseCompletedBatches;
		this.courseContactStaffId = courseContactStaffId;
		this.courseProspectusUrl = courseProspectusUrl;
		this.courseNoOfSems = courseNoOfSems;
	}
	public int getCourseNoOfSems() {
		return courseNoOfSems;
	}
	public void setCourseNoOfSems(int courseNoOfSems) {
		this.courseNoOfSems = courseNoOfSems;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public Float getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(Float courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getCourseDurationUnit() {
		return courseDurationUnit;
	}
	public void setCourseDurationUnit(String courseDurationUnit) {
		this.courseDurationUnit = courseDurationUnit;
	}
	public String getCourseExamCategory() {
		return courseExamCategory;
	}
	public void setCourseExamCategory(String courseExamCategory) {
		this.courseExamCategory = courseExamCategory;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	public Date getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public int getCourseCompletedBatches() {
		return courseCompletedBatches;
	}
	public void setCourseCompletedBatches(int courseCompletedBatches) {
		this.courseCompletedBatches = courseCompletedBatches;
	}
	public int getCourseContactStaffId() {
		return courseContactStaffId;
	}
	public void setCourseContactStaffId(int courseContactStaffId) {
		this.courseContactStaffId = courseContactStaffId;
	}
	public String getCourseProspectusUrl() {
		return courseProspectusUrl;
	}
	public void setCourseProspectusUrl(String courseProspectusUrl) {
		this.courseProspectusUrl = courseProspectusUrl;
	}
	public Course(int courseId) {
		super();
		this.courseId = courseId;
	}
	public Course(@NotBlank String courseId) {
		this.courseId = Integer.parseInt(courseId);
	}
	public Course() {
		super();
	}
	public String getCourseEnteredBy() {
		return courseEnteredBy;
	}
	public void setCourseEnteredBy(String courseEnteredBy) {
		this.courseEnteredBy = courseEnteredBy;
	}
	public String getCourseVerifiedBy() {
		return courseVerifiedBy;
	}
	public void setCourseVerifiedBy(String courseVerifiedBy) {
		this.courseVerifiedBy = courseVerifiedBy;
	}
	public Date getCourseEnteredOn() {
		return courseEnteredOn;
	}
	public void setCourseEnteredOn(Date courseEnteredOn) {
		this.courseEnteredOn = courseEnteredOn;
	}
	public Date getCourseVerifiedOn() {
		return courseVerifiedOn;
	}
	public void setCourseVerifiedOn(Date courseVerifiedOn) {
		this.courseVerifiedOn = courseVerifiedOn;
	}
	public Course(int courseId, @NotBlank String courseCode, @NotBlank String courseTitle, Float courseDuration,
			String courseDurationUnit, @NotBlank String courseExamCategory, String courseStatus, Date courseStartDate,
			int courseCompletedBatches, int courseContactStaffId, String courseProspectusUrl, int courseNoOfSems,
			String courseEnteredBy, String courseVerifiedBy, Date courseEnteredOn, Date courseVerifiedOn) {
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.courseDuration = courseDuration;
		this.courseDurationUnit = courseDurationUnit;
		this.courseExamCategory = courseExamCategory;
		this.courseStatus = courseStatus;
		this.courseStartDate = courseStartDate;
		this.courseCompletedBatches = courseCompletedBatches;
		this.courseContactStaffId = courseContactStaffId;
		this.courseProspectusUrl = courseProspectusUrl;
		this.courseNoOfSems = courseNoOfSems;
		this.courseEnteredBy = courseEnteredBy;
		this.courseVerifiedBy = courseVerifiedBy;
		this.courseEnteredOn = courseEnteredOn;
		this.courseVerifiedOn = courseVerifiedOn;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseTitle=" + courseTitle
				+ ", courseDuration=" + courseDuration + ", courseDurationUnit=" + courseDurationUnit
				+ ", courseExamCategory=" + courseExamCategory + ", courseStatus=" + courseStatus + ", courseStartDate="
				+ courseStartDate + ", courseCompletedBatches=" + courseCompletedBatches + ", courseContactStaffId="
				+ courseContactStaffId + ", courseProspectusUrl=" + courseProspectusUrl + ", courseNoOfSems="
				+ courseNoOfSems + ", courseEnteredBy=" + courseEnteredBy + ", courseVerifiedBy=" + courseVerifiedBy
				+ ", courseEnteredOn=" + courseEnteredOn + ", courseVerifiedOn=" + courseVerifiedOn + "]";
	}
	
	
}
