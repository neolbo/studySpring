package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체가 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 객체 인스턴스 필요시 getInstance 사용
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private로 생성하여 new를 이용하여 새로이 객체를 만들 수 없게 만든다
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로칙 호출");
    }
}
