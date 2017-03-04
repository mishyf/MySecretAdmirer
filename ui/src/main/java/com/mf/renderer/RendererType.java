package com.mf.renderer;

/**
 * Created by mishyf on 3/4/2017.
 */
public enum RendererType {

    WEB ("HTML_BASIC"),
    MOBILE ("PRIMEFACES_MOBILE");

    private String renderKit;

    RendererType(String renderKit) {
        this.renderKit = renderKit;
    }

    public String getRenderKit() {
        return this.renderKit;
    }

    public static RendererType findRenderMode(String renderKit) {
        RendererType renderMode = RendererType.WEB;
        for (RendererType rendererType : RendererType.values()) {
            if(rendererType.getRenderKit().equals(renderKit)) {
                return rendererType;
            }
        }
        return renderMode;
    }
}
