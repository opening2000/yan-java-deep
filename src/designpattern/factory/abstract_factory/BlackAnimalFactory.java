package designpattern.factory.abstract_factory;

public class BlackAnimalFactory implements IAnimalFactory{

	public ICat createCat() {
		return new BlackCat();
	}

	public IDog createDog() {
		return new BlackDog();
	}

}
