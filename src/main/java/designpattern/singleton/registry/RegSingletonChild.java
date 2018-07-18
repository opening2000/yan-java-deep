package designpattern.singleton.registry;

// 登记式单例类的子类
public class RegSingletonChild extends RegSingleton{
	public RegSingletonChild() {
	}

	// 静态工厂方法
	public static RegSingletonChild getInstance(){ 
        return (RegSingletonChild)RegSingleton.getInstance("RegSingleton");
    }

	public String about() {
		return "Hello, I am RegSingletonChild";
	}
}
