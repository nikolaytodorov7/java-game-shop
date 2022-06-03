package ogs.exception;

import java.util.Objects;
import java.util.StringJoiner;

public class ConstraintViolation {
    private String type;
    private String field;
    private Object invalidValue;
    private String errorMessage;

    public ConstraintViolation() {
    }

    public ConstraintViolation(String type, String field, Object invalidValue, String errorMessage) {
        this.type = type;
        this.field = field;
        this.invalidValue = invalidValue;
        this.errorMessage = errorMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstraintViolation that = (ConstraintViolation) o;

        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(field, that.field)) return false;
        if (!Objects.equals(invalidValue, that.invalidValue)) return false;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (invalidValue != null ? invalidValue.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConstraintViolation.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("field='" + field + "'")
                .add("invalidValue=" + invalidValue)
                .add("errorMessage='" + errorMessage + "'")
                .toString();
    }
}
