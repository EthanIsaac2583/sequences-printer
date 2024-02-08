package kz.ruanjian;

import java.time.LocalDateTime;
import java.util.Objects;

public class PrintEvent {

    private LocalDateTime dateTime;
    private String name;
    private Object value;

    public PrintEvent() {
    }

    private PrintEvent(Builder builder) {
        dateTime = builder.dateTime;
        name = builder.name;
        value = builder.value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        PrintEvent event = (PrintEvent) o;
        return Objects.equals(name, event.name) && Objects.equals(value, event.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private LocalDateTime dateTime;
        private String name;
        private Object value;

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        public PrintEvent build() {
            return new PrintEvent(this);
        }
    }
}
