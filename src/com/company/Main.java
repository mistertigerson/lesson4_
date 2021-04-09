package com.company;

import java.util.Random;

public class Main {
    public static int[] heroesHealth = {270, 280 , 250, 350};
    public static String[] heroesNames = {"LU KANG ", "JAX ", "SCORPION ", "MEDIC "};
    public static int[] heroesStrike = {20,15 ,25, 0};


    public static String bossName  = "SHAO KAHN ";
    public static int bossHealth = 1000;
    public static int bossStrike = 50;
    public static String superStrike;
    public static int roundNumber = 0;




    public static void main(String[] args) {
        printStaticts();
        System.out.println("-------game is started--------");

        while (!isGameFinished()){
            round();
        }

    }

    public static void round(){
        roundNumber++;
        System.out.println("----------- round " + roundNumber + "-------------");
        superStrike =  getSuperStrikeHero();
        bossHits();
        heroesHits();
        printStaticts();
        medicHeal();




    }
    public static void medicHeal() {
        Random random = new Random();
        int randomHealth = random.nextInt(150) + 20;
        int randomIndex = random.nextInt(heroesHealth.length);

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] == heroesHealth[3]) {
                break;
            }
            if (heroesHealth[3]>0 && heroesHealth[randomIndex]<100 && heroesHealth[randomIndex] >0 ) {
                if (heroesHealth[i] == heroesHealth[randomIndex]) {
                    heroesHealth[randomIndex] += randomHealth;
                    System.out.println("medic вылечил " + heroesNames[i] + " на " + randomHealth);
                }
            }

        }



    }
    public static boolean  isGameFinished() {
        if (bossHealth == 10 ) {
            System.out.println("finish him ");
            return true;
        } else if (bossHealth <= 0) {
            System.out.println("heroes win. Game over ");
            return true;
        }
        boolean allHeroesaredied = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesaredied = false;
                break;
            }
        }
        if (allHeroesaredied) {
            System.out.println(bossName +  "win" + "\n" + "game over");

        }
        return allHeroesaredied;
    }

    public static void heroesHits(){
        Random random = new Random();
        int coef =  random.nextInt(9) + 2;
        for (int i = 0; i <heroesStrike.length ; i++) {
            if (heroesStrike[i] == heroesStrike[3]){
                break;
            }
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (superStrike == heroesNames[i]) {
                    bossHealth = bossHealth - (heroesStrike[i] * coef) ;
                    System.out.println("super strikeDamage = " + superStrike + " " + (heroesStrike[i] * coef) );
                } else {
                    bossHealth = bossHealth - heroesStrike[i];
                }
                if (bossHealth <  0) {
                    bossHealth = 0;
                }
            }

        }
    }

    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i]>0 && bossHealth>0 ) {
                heroesHealth[i] = heroesHealth[i] - bossStrike;
            }
            if (heroesHealth[i] <= 0){
                heroesHealth[i] = 0;

            }

        }
    }

    public static String getSuperStrikeHero(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }
    public static void printStaticts(){
        System.out.println(bossName + " Health = " + bossHealth + " strike [ " + bossStrike + " ] ");

        for (int i = 0; i <heroesNames.length ; i++) {
            System.out.println(heroesNames[i] + "health = " + heroesHealth[i] + " strike [ " + heroesStrike[i] + " ]  ");

        }
    }
}

