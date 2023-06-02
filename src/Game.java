import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz :");
        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " savaş adasına hoşgeldiniz !");
        System.out.println("Lütfen bir karakter seçiniz !:");
        player.selectCharacter();

        Location location = null;
        while (true){
            player.printPlayerInfo();
            System.out.println();
            System.out.println("######* Bölgeler *######");
            System.out.println();
            System.out.println("1 - Güvenli Ev --> Burada düşman yoktur !");
            System.out.println("2 - Envanter Dükkanı --> Burada Silah veya zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara --> Ödül : *Yemek* , dikkatli ol Zombie var!");
            System.out.println("4 - Ormana --> Ödül : *Odun* , dikkatli ol Vampir var!");
            System.out.println("5 - Nehir --> Ödül : *Su* , dikkatli ol Ayı var!");
            System.out.println("6 - Taş Madeni --> Ödül : *Silah veya zırh* , dikkatli ol Yılan var!");
            System.out.println("0 - Çıkış --> Oyunu Bitir!");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz :");
            int selectLocation = input.nextInt();
            switch (selectLocation){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Quarry(player);
                    break;
                default:
                    System.out.println("Hatalı bölge! Geçerli bir bölge giriniz!!!");
                    continue;
            }
            if (location == null){
                System.out.println("Oyun bitti! İyi günler...");
                break;
            }
            if (!location.onLocation()){
                System.out.println("Oyun Bitti !");
                break;
            }



        }


    }
}