package com.juanjiga.crud_db;

// Clase "POJO" Plain Old Java Object

public class Pojo {

        private int ID;
        private String Name;
        private String User;
        private String Password;

        public Pojo(){};

        public Pojo(String name, String user, String password) {
            this.Name = name;
            this.User = user;
            this.Password = password;
        }

        public Pojo(int id, String name, String user, String password) {
            this.ID = id;
            this.Name = name;
            this.User = user;
            this.Password = password;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            this.Name = name;
        }

        public String getUser() {
            return User;
        }

        public void setUser(String user) {
            this.User = user;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            this.Password = password;
        }

}



