import java.util.Random;

public abstract class BattleLocation extends Location {
    private Monster monster;
    private String award;
    private int maxMonster;
    public BattleLocation(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }
    private static final Random random = new Random();
    @Override
    public boolean onLocation() {
        int monsterNumber = this.randomMonsterNumber();
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + monsterNumber+ " tane " + this.getMonster().getName() + " yaşıyor !");
        System.out.println("<S>Savaş veya <K>Kaç! :");
        String selectBattleCase = Location.input.nextLine();
        selectBattleCase = selectBattleCase.toUpperCase();

        if (selectBattleCase.equals("S") && combat(monsterNumber)){
                System.out.println(this.getName() + " tüm düşmanları yendiniz !");
                return true;
        }

    if (this.getPlayer().getHealth() <= 0){
        System.out.println("Öldünüz !!!");
    return false;
    }
        return true;
    }
    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getDefaultHealth());
            playerStats();
            monsterStats(i);

            boolean isFirstPlayerTurn = decideFirstPlayerTurn();

            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                if (isFirstPlayerTurn) {
                    System.out.print("<V>ur veya <K>aç: ");
                    String selectCombat = Location.input.nextLine().toUpperCase();

                    if (selectCombat.equals("V")) {
                        System.out.println("Siz vurdunuz!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu!");
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                } else {
                    System.out.println("Sıra canavarda!");

                    int monsterAttack = rastgeleSayi(3, 6);
                    this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterAttack);
                    System.out.println("Canavar size vurdu! Canavarın hasarı: " + monsterAttack);
                    afterHit();

                    if (this.getPlayer().getHealth() <= 0) {
                        return false;
                    }

                    System.out.println();
                    System.out.println("Sıra sizde!");
                }

                isFirstPlayerTurn = !isFirstPlayerTurn;
            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz!");
                System.out.println(this.getMonster().getAward() + " para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + getMonster().getAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }



    private static boolean decideFirstPlayerTurn() {
        return random.nextBoolean();
    }

    private static int rastgeleSayi(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " canı : " + this.getMonster().getHealth());
        System.out.println("--------------------------------------");
    }
    public void playerStats() {
        System.out.println("**************************************");
        System.out.println("Oyuncu Değerleri :");
        System.out.println("--------------------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Zırh Düzeyi : " + getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
    }
    private void monsterStats(int i) {
        System.out.println("**************************************");
        System.out.println(i + ". " + this.getMonster().getName() + " değerleri");
        System.out.println("--------------------------------------");
        System.out.println("Sağlık : " + getMonster().getHealth());
        System.out.println("Hasar : " + getMonster().getDamage());
        System.out.println("Ödül : " + getMonster().getAward());
    }

    public int randomMonsterNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
