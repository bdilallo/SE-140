package edu.depaul;

import java.util.ArrayList;

public interface Cart_Builder {
	Cart_Builder setName(String cartName);
	Cart_Builder setContents(ArrayList<Product> cartContents);
	Cart_Builder setTotal(double cartTotal);
	Cart build();
}
