package ar.com.chepps.Companny.enums;

public enum TipoPago {
    EFECTIVO("EFECTIVO"),
    TRANSFERENCIA("TRANSFERENCIA"),
    VILLETERAS_VIRTUALES("VILLETERAS VIRTUALES (QR, LINK, ETC.)"),
    OTROS("OTRO (cheques, pagaré, cuenta corriente, etc.)");

    private final String descripcion;

    TipoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
