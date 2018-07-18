package designpattern.factory.abstract_factory;

public class WhiteAnimalFactory implements IAnimalFactory{

	public ICat createCat() {
		return new WhiteCat();
	}

	public IDog createDog() {
		return new WhiteDog();
	}

}
