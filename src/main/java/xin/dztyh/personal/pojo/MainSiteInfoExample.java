package xin.dztyh.personal.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainSiteInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MainSiteInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSignOneIsNull() {
            addCriterion("sign_one is null");
            return (Criteria) this;
        }

        public Criteria andSignOneIsNotNull() {
            addCriterion("sign_one is not null");
            return (Criteria) this;
        }

        public Criteria andSignOneEqualTo(String value) {
            addCriterion("sign_one =", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneNotEqualTo(String value) {
            addCriterion("sign_one <>", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneGreaterThan(String value) {
            addCriterion("sign_one >", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneGreaterThanOrEqualTo(String value) {
            addCriterion("sign_one >=", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneLessThan(String value) {
            addCriterion("sign_one <", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneLessThanOrEqualTo(String value) {
            addCriterion("sign_one <=", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneLike(String value) {
            addCriterion("sign_one like", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneNotLike(String value) {
            addCriterion("sign_one not like", value, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneIn(List<String> values) {
            addCriterion("sign_one in", values, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneNotIn(List<String> values) {
            addCriterion("sign_one not in", values, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneBetween(String value1, String value2) {
            addCriterion("sign_one between", value1, value2, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignOneNotBetween(String value1, String value2) {
            addCriterion("sign_one not between", value1, value2, "signOne");
            return (Criteria) this;
        }

        public Criteria andSignTwoIsNull() {
            addCriterion("sign_two is null");
            return (Criteria) this;
        }

        public Criteria andSignTwoIsNotNull() {
            addCriterion("sign_two is not null");
            return (Criteria) this;
        }

        public Criteria andSignTwoEqualTo(String value) {
            addCriterion("sign_two =", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoNotEqualTo(String value) {
            addCriterion("sign_two <>", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoGreaterThan(String value) {
            addCriterion("sign_two >", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoGreaterThanOrEqualTo(String value) {
            addCriterion("sign_two >=", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoLessThan(String value) {
            addCriterion("sign_two <", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoLessThanOrEqualTo(String value) {
            addCriterion("sign_two <=", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoLike(String value) {
            addCriterion("sign_two like", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoNotLike(String value) {
            addCriterion("sign_two not like", value, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoIn(List<String> values) {
            addCriterion("sign_two in", values, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoNotIn(List<String> values) {
            addCriterion("sign_two not in", values, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoBetween(String value1, String value2) {
            addCriterion("sign_two between", value1, value2, "signTwo");
            return (Criteria) this;
        }

        public Criteria andSignTwoNotBetween(String value1, String value2) {
            addCriterion("sign_two not between", value1, value2, "signTwo");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlIsNull() {
            addCriterion("background_url is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlIsNotNull() {
            addCriterion("background_url is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlEqualTo(String value) {
            addCriterion("background_url =", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotEqualTo(String value) {
            addCriterion("background_url <>", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlGreaterThan(String value) {
            addCriterion("background_url >", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlGreaterThanOrEqualTo(String value) {
            addCriterion("background_url >=", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlLessThan(String value) {
            addCriterion("background_url <", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlLessThanOrEqualTo(String value) {
            addCriterion("background_url <=", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlLike(String value) {
            addCriterion("background_url like", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotLike(String value) {
            addCriterion("background_url not like", value, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlIn(List<String> values) {
            addCriterion("background_url in", values, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotIn(List<String> values) {
            addCriterion("background_url not in", values, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlBetween(String value1, String value2) {
            addCriterion("background_url between", value1, value2, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andBackgroundUrlNotBetween(String value1, String value2) {
            addCriterion("background_url not between", value1, value2, "backgroundUrl");
            return (Criteria) this;
        }

        public Criteria andResumeFileIsNull() {
            addCriterion("resume_file is null");
            return (Criteria) this;
        }

        public Criteria andResumeFileIsNotNull() {
            addCriterion("resume_file is not null");
            return (Criteria) this;
        }

        public Criteria andResumeFileEqualTo(String value) {
            addCriterion("resume_file =", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileNotEqualTo(String value) {
            addCriterion("resume_file <>", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileGreaterThan(String value) {
            addCriterion("resume_file >", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileGreaterThanOrEqualTo(String value) {
            addCriterion("resume_file >=", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileLessThan(String value) {
            addCriterion("resume_file <", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileLessThanOrEqualTo(String value) {
            addCriterion("resume_file <=", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileLike(String value) {
            addCriterion("resume_file like", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileNotLike(String value) {
            addCriterion("resume_file not like", value, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileIn(List<String> values) {
            addCriterion("resume_file in", values, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileNotIn(List<String> values) {
            addCriterion("resume_file not in", values, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileBetween(String value1, String value2) {
            addCriterion("resume_file between", value1, value2, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andResumeFileNotBetween(String value1, String value2) {
            addCriterion("resume_file not between", value1, value2, "resumeFile");
            return (Criteria) this;
        }

        public Criteria andPersonalPicIsNull() {
            addCriterion("personal_pic is null");
            return (Criteria) this;
        }

        public Criteria andPersonalPicIsNotNull() {
            addCriterion("personal_pic is not null");
            return (Criteria) this;
        }

        public Criteria andPersonalPicEqualTo(String value) {
            addCriterion("personal_pic =", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicNotEqualTo(String value) {
            addCriterion("personal_pic <>", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicGreaterThan(String value) {
            addCriterion("personal_pic >", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicGreaterThanOrEqualTo(String value) {
            addCriterion("personal_pic >=", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicLessThan(String value) {
            addCriterion("personal_pic <", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicLessThanOrEqualTo(String value) {
            addCriterion("personal_pic <=", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicLike(String value) {
            addCriterion("personal_pic like", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicNotLike(String value) {
            addCriterion("personal_pic not like", value, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicIn(List<String> values) {
            addCriterion("personal_pic in", values, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicNotIn(List<String> values) {
            addCriterion("personal_pic not in", values, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicBetween(String value1, String value2) {
            addCriterion("personal_pic between", value1, value2, "personalPic");
            return (Criteria) this;
        }

        public Criteria andPersonalPicNotBetween(String value1, String value2) {
            addCriterion("personal_pic not between", value1, value2, "personalPic");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("modify_date is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("modify_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("modify_date =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("modify_date <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("modify_date >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_date >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("modify_date <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("modify_date <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("modify_date in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("modify_date not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("modify_date between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("modify_date not between", value1, value2, "modifyDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}