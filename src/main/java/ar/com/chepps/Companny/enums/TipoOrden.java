package ar.com.chepps.Companny.enums;

public enum TipoOrden {
    PAGO("PAGO"),
    COMPRA("COMPRA"),
    VENTA("VENTA"),
    AGREGACION_DE_STOCK("AGREGACIÓN DE STOCK"),
    DEVOLUCION_O_ELIMINACION_DE_STOCK("DEVOLUCIÓN O ELIMINACIÓN DE STOCK");

    private final String label;

    TipoOrden(String label) {
        this.label = label;
    }
}