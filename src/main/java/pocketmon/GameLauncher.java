package pocketmon;

import java.util.*;

public class GameLauncher {

    public static void main(String[] args) {
        //트레이너 더미데이터 생성
        List<Trainer> trainerList = new ArrayList<>();
        Trainer player = new Trainer("한지우");
        Trainer trainer2 = new Trainer("오박사");
        Trainer trainer3 = new Trainer("이슬");
        Trainer trainer4 = new Trainer("웅");

        Scanner scanner = new Scanner(System.in);

        //트레이너 더미데이터 생성
        trainerList.add(player);
        trainerList.add(trainer2);
        trainerList.add(trainer3);
        trainerList.add(trainer4);

        //트레이너가 보유한 포켓몬 더미데이터
        Pokemon pikachu = new Pokemon("피카츄", 50, 5);
        trainer2.capturedPokemonList.add(pikachu);
        trainer2.capturedPokemonByName.put(pikachu.getPokemonName(), pikachu);
        trainer3.capturedPokemonList.add(pikachu);
        trainer3.capturedPokemonByName.put(pikachu.getPokemonName(), pikachu);

        while (true) {
            System.out.println("\n==== 포켓몬 게임 ====");
            System.out.println("1: 전투 시작");
            System.out.println("2: 도감 검색");
            System.out.println("3: 포켓몬 특수 능력 사용");
            System.out.println("4: 현재 가진 포켓몬 보기");
            //포켓몬 트레이드 기능
            System.out.println("5: 포켓몬 교환하기");
            System.out.println("6: 종료");
            System.out.print("원하는 기능을 선택하세요: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // 전투 기능
                    Pokemon wildPokemon = player.encounterWildPokemon();
                    player.hunt(wildPokemon);
                    break;

                case "2":
                    // 도감 검색 기능
                    player.explorePokeDex();
                    break;

                case "3":
                    // 특수 능력 사용
                    player.showSpecialAbilityPokemon();
                    System.out.println("특수 능력을 사용할 포켓몬 이름을 입력하세요:");
                    String specialPokemonName = scanner.nextLine().trim();
                    player.useSpecialAbility(specialPokemonName);
                    break;

                case "4":
                    // 현재 가진 포켓몬 보기
                    player.showOwnedPokemon();
                    break;

                case "5":
                    //포켓몬 교환
                    // +@ TODO : 교환 상대 고르는 기능 만들기, 교환기능 메소드화

                    //교환 신청할 대상 트레이너 선택
                    System.out.println("\n======= 교환 신청할 대상 트레이너 =======");
                    for (int i = 0; i < trainerList.size(); i++) {
                        System.out.println((i + 1) + ". " + trainerList.get(i));
                    }
                    System.out.println("===================================");
                    System.out.println("트레이너 번호 입력 : ");
                    int trainerNum = scanner.nextInt();
                    Trainer tgTrainer = trainerList.get(trainerNum - 1);

                    //상대 포켓몬 리스트 출력
                    System.out.println("\n교환 가능한 상대의 포켓몬 : ");
                    tgTrainer.showOwnedPokemon();
                    System.out.println("상대의 포켓몬 이름 : ");
                    String tgPokemon = scanner.nextLine();
                    Pokemon tgPoke = tgTrainer.capturedPokemonByName.get(tgPokemon);
                    System.out.println("내 포켓몬 이름 : ");
                    String myPokemon = scanner.nextLine();
                    player.tradePokemon(tgTrainer, tgPokemon,  myPokemon);
                    //진화 후 리스트 업데이트
                    Pokemon evolvedPoke = tgPoke.evolve();

                    player.capturedPokemonByName.remove(tgPokemon);
                    player.capturedPokemonList.remove(tgPoke);
                    player.capturedPokemonByName.put(evolvedPoke.getPokemonName(), evolvedPoke); //이름 바꿔야함
                    player.capturedPokemonList.add(evolvedPoke);

                    player.showOwnedPokemon();
                    break;

                case "6":
                    // 종료
                    System.out.println("게임을 종료합니다. 감사합니다!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 1, 2, 3, 4, 5 중에서 선택하세요.");
            }

        }
    }
}
