package algs.secondTask;

public class Client {
    private static class FIO{
        String surname;
        String name;
        String lastname;

        public FIO(String surname, String name, String lastname) {
            this.surname = surname;
            this.name = name;
            this.lastname = lastname;
        }

        @Override
        public String toString() {
            return "{surname:%s, name:%s, lastname:%s}".formatted(surname, name, lastname);
        }
    }

    public enum Plan {
        START ("Оплата осуществляется по факту предоставления услуги"),
        PRO("10$ за безлимитный интернет и тв"),
        PLANIEST("15$ за безлимитный интернет, тв, звонки по Америке"),
        SMART("8$ за безлимитный интернет"),
        SMARTPLUS("10$ за безлимитный интернет и бесплатные звонки по Штату"),
        FORFAMILY("20$ за безлимитный интернет и тв для всей семьи"),
        FORGRANDFATHER("Чтобы ваш дедушка мог всегда быть на связи"),
        X("Условия определяются случайно при подключении");

        private String description;

        Plan(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "{description:%s}".formatted(getDescription());
        }
    }

    private String phoneNumber;
    private FIO fio;
    private Plan plan;

    private Client(String phoneNumber, FIO fio, Plan plan) {
        this.phoneNumber = phoneNumber;
        this.fio = fio;
        this.plan = plan;
    }

    public static Client buildClient(String phoneNumber, String lastname, String name, String surname, Plan plan) {
        return new Client(phoneNumber, new FIO(surname, name, lastname), plan);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public FIO getFio() {
        return fio;
    }

    public Plan getPlan() {
        return plan;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(phoneNumber.substring(2));
    }

    @Override
    public String toString() {
        return "{phone:%s, fio:%s, plan: %s}".formatted(phoneNumber, fio.toString(), plan.toString());
    }
}
