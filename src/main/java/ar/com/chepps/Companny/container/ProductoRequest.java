package ar.com.chepps.Companny.container;

import ar.com.chepps.Companny.entity.ProductoDetalle;
import ar.com.chepps.Companny.entity.ProductoManufacturado;

public class ProductoRequest {
    private ProductoManufacturado productoManufacturado;
    private ProductoDetalle detalle;

    public ProductoManufacturado getProductoManufacturado() {
        return productoManufacturado;
    }

    public void setProductoManufacturado(ProductoManufacturado productoManufacturado) {
        this.productoManufacturado = productoManufacturado;
    }

    public ProductoDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ProductoDetalle detalle) {
        this.detalle = detalle;
    }
}
