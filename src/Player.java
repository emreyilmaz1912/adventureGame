import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int defaultHealth;
    private int money;
    private String name;
    private String characterName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;
    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectCharacter() {
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Monster.Archer archer = new Monster.Archer();

        GameCharacter[] charactersList = {new Samurai(), new Monster.Archer(), new Knight()};

        System.out.println("--------------------------------------------------------------");
        for (GameCharacter gameCharacter : charactersList) {
            System.out.println("ID : " + gameCharacter.getId() +
                    "\t Karakter: " + gameCharacter.getName() +
                    "\t Hasar: " + gameCharacter.getDamage() +
                    "\t Sağlık: " + gameCharacter.getHealth() +
                    "\t Para: " + gameCharacter.getMoney());
        }
        System.out.println("--------------------------------------------------------------");
        System.out.print("Lütfen bir karakter seçiniz :");
        int selectCharacter =input.nextInt();
        switch (selectCharacter){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Monster.Archer());
            case 3:
                initPlayer(new Knight());
            default:
                initPlayer(new Samurai());
        }

    }


    public void initPlayer(GameCharacter gameCharacter){
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setDefaultHealth(gameCharacter.getHealth());
        this.setMoney(gameCharacter.getMoney());
        this.setCharacterName(gameCharacter.getName());
    }
    public void printPlayerInfo(){
        System.out.println("Silah :  " + this.getInventory().getWeapon().getName() +
                ", Zırh : " + this.getInventory().getArmor().getName() +
                ", Zırh Düzeyi : " + getInventory().getArmor().getBlock() +
                ", Hasarınız : " + this.getTotalDamage() +
                ", Sağlığınız : "  + this.getHealth() +
                ", Paranız : " + this.getMoney());
    }
    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int coin) {
        this.money = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }
}
