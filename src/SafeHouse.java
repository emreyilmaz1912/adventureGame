public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz !");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        System.out.println("Canınız Yenilendi !");

        return true;
    }
}
