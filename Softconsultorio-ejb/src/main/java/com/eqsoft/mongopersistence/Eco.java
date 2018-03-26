package com.eqsoft.mongopersistence;

import java.util.Date;

public interface Eco {
    Date getFechaEco();

    Integer getPk();

    void setPk(Integer pk);

    String tipo();
}
