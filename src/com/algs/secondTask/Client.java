package com.algs.secondTask;

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
        public int hashCode() {
            int hash = 7;
            String allStrings = surname + name + lastname;
            for(int i = 0; i < allStrings.length(); i++){
                hash = hash*31 + allStrings.charAt(i);
            }
            return hash;
        }
    }

    public enum Plan {
        SIMPLE("This is simple telephone plan"),
        DIFFICULT("This is difficult telephone plan");
        private String description;

        Plan(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
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
        return (phoneNumber.hashCode() + fio.hashCode() + plan.hashCode()) % Integer.MAX_VALUE;
    }
}
