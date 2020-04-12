package net.togogo.entity;

import java.util.ArrayList;
import java.util.List;

public class PurchasingdetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchasingdetailExample() {
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

        public Criteria andPdIdIsNull() {
            addCriterion("pd_id is null");
            return (Criteria) this;
        }

        public Criteria andPdIdIsNotNull() {
            addCriterion("pd_id is not null");
            return (Criteria) this;
        }

        public Criteria andPdIdEqualTo(String value) {
            addCriterion("pd_id =", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdNotEqualTo(String value) {
            addCriterion("pd_id <>", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdGreaterThan(String value) {
            addCriterion("pd_id >", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdGreaterThanOrEqualTo(String value) {
            addCriterion("pd_id >=", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdLessThan(String value) {
            addCriterion("pd_id <", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdLessThanOrEqualTo(String value) {
            addCriterion("pd_id <=", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdLike(String value) {
            addCriterion("pd_id like", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdNotLike(String value) {
            addCriterion("pd_id not like", value, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdIn(List<String> values) {
            addCriterion("pd_id in", values, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdNotIn(List<String> values) {
            addCriterion("pd_id not in", values, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdBetween(String value1, String value2) {
            addCriterion("pd_id between", value1, value2, "pdId");
            return (Criteria) this;
        }

        public Criteria andPdIdNotBetween(String value1, String value2) {
            addCriterion("pd_id not between", value1, value2, "pdId");
            return (Criteria) this;
        }

        public Criteria andPIdIsNull() {
            addCriterion("p_id is null");
            return (Criteria) this;
        }

        public Criteria andPIdIsNotNull() {
            addCriterion("p_id is not null");
            return (Criteria) this;
        }

        public Criteria andPIdEqualTo(String value) {
            addCriterion("p_id =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(String value) {
            addCriterion("p_id <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(String value) {
            addCriterion("p_id >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(String value) {
            addCriterion("p_id >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(String value) {
            addCriterion("p_id <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(String value) {
            addCriterion("p_id <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLike(String value) {
            addCriterion("p_id like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotLike(String value) {
            addCriterion("p_id not like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<String> values) {
            addCriterion("p_id in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<String> values) {
            addCriterion("p_id not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(String value1, String value2) {
            addCriterion("p_id between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(String value1, String value2) {
            addCriterion("p_id not between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPdNumIsNull() {
            addCriterion("pd_num is null");
            return (Criteria) this;
        }

        public Criteria andPdNumIsNotNull() {
            addCriterion("pd_num is not null");
            return (Criteria) this;
        }

        public Criteria andPdNumEqualTo(Double value) {
            addCriterion("pd_num =", value, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumNotEqualTo(Double value) {
            addCriterion("pd_num <>", value, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumGreaterThan(Double value) {
            addCriterion("pd_num >", value, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumGreaterThanOrEqualTo(Double value) {
            addCriterion("pd_num >=", value, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumLessThan(Double value) {
            addCriterion("pd_num <", value, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumLessThanOrEqualTo(Double value) {
            addCriterion("pd_num <=", value, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumIn(List<Double> values) {
            addCriterion("pd_num in", values, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumNotIn(List<Double> values) {
            addCriterion("pd_num not in", values, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumBetween(Double value1, Double value2) {
            addCriterion("pd_num between", value1, value2, "pdNum");
            return (Criteria) this;
        }

        public Criteria andPdNumNotBetween(Double value1, Double value2) {
            addCriterion("pd_num not between", value1, value2, "pdNum");
            return (Criteria) this;
        }

        public Criteria andSIdIsNull() {
            addCriterion("s_id is null");
            return (Criteria) this;
        }

        public Criteria andSIdIsNotNull() {
            addCriterion("s_id is not null");
            return (Criteria) this;
        }

        public Criteria andSIdEqualTo(String value) {
            addCriterion("s_id =", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotEqualTo(String value) {
            addCriterion("s_id <>", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThan(String value) {
            addCriterion("s_id >", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThanOrEqualTo(String value) {
            addCriterion("s_id >=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThan(String value) {
            addCriterion("s_id <", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThanOrEqualTo(String value) {
            addCriterion("s_id <=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLike(String value) {
            addCriterion("s_id like", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotLike(String value) {
            addCriterion("s_id not like", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdIn(List<String> values) {
            addCriterion("s_id in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotIn(List<String> values) {
            addCriterion("s_id not in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdBetween(String value1, String value2) {
            addCriterion("s_id between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotBetween(String value1, String value2) {
            addCriterion("s_id not between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andKIdIsNull() {
            addCriterion("k_id is null");
            return (Criteria) this;
        }

        public Criteria andKIdIsNotNull() {
            addCriterion("k_id is not null");
            return (Criteria) this;
        }

        public Criteria andKIdEqualTo(String value) {
            addCriterion("k_id =", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotEqualTo(String value) {
            addCriterion("k_id <>", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdGreaterThan(String value) {
            addCriterion("k_id >", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdGreaterThanOrEqualTo(String value) {
            addCriterion("k_id >=", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdLessThan(String value) {
            addCriterion("k_id <", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdLessThanOrEqualTo(String value) {
            addCriterion("k_id <=", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdLike(String value) {
            addCriterion("k_id like", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotLike(String value) {
            addCriterion("k_id not like", value, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdIn(List<String> values) {
            addCriterion("k_id in", values, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotIn(List<String> values) {
            addCriterion("k_id not in", values, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdBetween(String value1, String value2) {
            addCriterion("k_id between", value1, value2, "kId");
            return (Criteria) this;
        }

        public Criteria andKIdNotBetween(String value1, String value2) {
            addCriterion("k_id not between", value1, value2, "kId");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceIsNull() {
            addCriterion("purchasingprice is null");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceIsNotNull() {
            addCriterion("purchasingprice is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceEqualTo(Double value) {
            addCriterion("purchasingprice =", value, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceNotEqualTo(Double value) {
            addCriterion("purchasingprice <>", value, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceGreaterThan(Double value) {
            addCriterion("purchasingprice >", value, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("purchasingprice >=", value, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceLessThan(Double value) {
            addCriterion("purchasingprice <", value, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceLessThanOrEqualTo(Double value) {
            addCriterion("purchasingprice <=", value, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceIn(List<Double> values) {
            addCriterion("purchasingprice in", values, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceNotIn(List<Double> values) {
            addCriterion("purchasingprice not in", values, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceBetween(Double value1, Double value2) {
            addCriterion("purchasingprice between", value1, value2, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPurchasingpriceNotBetween(Double value1, Double value2) {
            addCriterion("purchasingprice not between", value1, value2, "purchasingprice");
            return (Criteria) this;
        }

        public Criteria andPUnitIsNull() {
            addCriterion("p_unit is null");
            return (Criteria) this;
        }

        public Criteria andPUnitIsNotNull() {
            addCriterion("p_unit is not null");
            return (Criteria) this;
        }

        public Criteria andPUnitEqualTo(String value) {
            addCriterion("p_unit =", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitNotEqualTo(String value) {
            addCriterion("p_unit <>", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitGreaterThan(String value) {
            addCriterion("p_unit >", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitGreaterThanOrEqualTo(String value) {
            addCriterion("p_unit >=", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitLessThan(String value) {
            addCriterion("p_unit <", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitLessThanOrEqualTo(String value) {
            addCriterion("p_unit <=", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitLike(String value) {
            addCriterion("p_unit like", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitNotLike(String value) {
            addCriterion("p_unit not like", value, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitIn(List<String> values) {
            addCriterion("p_unit in", values, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitNotIn(List<String> values) {
            addCriterion("p_unit not in", values, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitBetween(String value1, String value2) {
            addCriterion("p_unit between", value1, value2, "pUnit");
            return (Criteria) this;
        }

        public Criteria andPUnitNotBetween(String value1, String value2) {
            addCriterion("p_unit not between", value1, value2, "pUnit");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Double value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Double value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Double value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Double value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Double value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Double> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Double> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Double value1, Double value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Double value1, Double value2) {
            addCriterion("amount not between", value1, value2, "amount");
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