import java.util.ArrayList;
import java.util.List;

public class lab15 {

    public static void main(String[] args) {
        Grupa mamix = new Grupa();//создаем группу мамикса
        Grupa eongay = new Grupa();//создаем группу ивангая
        Grupa litvin = new Grupa();//создаем группу литвина
        Dashboard mam = new Dashboard(mamix);
        Dashboard eon = new Dashboard(eongay);
        Dashboard lit = new Dashboard(litvin);

        mamix.changeData(10018390);
        eongay.changeData(2012083);
        litvin.changeData(6012830);
    }
}
interface Notifier{
    public void addObserver(Observer obs);

}

class Grupa implements Notifier{
    private List observers; // список наблюдателей
    private int podpischiki; // подписчики

    public Grupa(){
        observers = new ArrayList();
    }

    // добавить слушателя
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    // удалить слушателя
    public void removeObserver(Observer obs) {
        int i = observers.indexOf(obs);
        if (i >= 0){
            observers.remove(i);
        }
    }

    // уведомить слушателей
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++){
            Observer obs = (Observer)observers.get(i);
            obs.update(podpischiki);
        }
    }

    public void changeData(int podpischiki){
        this.podpischiki = podpischiki;
        notifyObserver(); // уведомляем подписчиков об изменениях
    }

}

interface Observer{
    public void update(int podpischiki);
}

class Dashboard implements Observer{
    private Notifier notifier;
    private int podpischiki; // подписчики

    public Dashboard(Notifier notifier){
        this.notifier = notifier;
        notifier.addObserver(this);
    }

    public void update(int podpischiki) {
        this.podpischiki = podpischiki;
        show();
    }

    // отображение информации
    public void show(){
        System.out.println("Количество подписчиков = " + podpischiki);
    }

}