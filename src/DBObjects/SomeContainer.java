package DBObjects;

import java.util.function.Supplier;

public class SomeContainer<E> {
	private Supplier<E> supplier;

	SomeContainer(Supplier<E> supplier) {
		this.supplier = supplier;
	}

	E createContents() {
		return supplier.get();
	}
}