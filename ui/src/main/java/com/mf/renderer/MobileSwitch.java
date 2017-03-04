package com.mf.renderer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mishyf on 3/4/2017.
 */
@ApplicationScoped
@ManagedBean(name = "mobileSwitch")
public class MobileSwitch {

    private static final String MOBILE_USER_AGENT_PATTERN = "/Mobile|iP(hone|od|ad)|Android|BlackBerry|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Blazer|Dolfin|Dolphin|Skyfire|Zune/";

    private RendererType findBrowserRenderMode() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        Pattern pattern = Pattern.compile(MOBILE_USER_AGENT_PATTERN);
        Matcher matcher = pattern.matcher(userAgent);
        return matcher.find() ? RendererType.MOBILE : RendererType.WEB;
    }

    public String findRenderMode() {
        return findBrowserRenderMode().name();
    }

    public void switchTo(String renderMode) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String viewId = facesContext.getViewRoot().getViewId();
        RendererType nextRenderMode = RendererType.valueOf(renderMode);
        ExternalContext externalContext = facesContext.getExternalContext();
        String context = externalContext.getRequestContextPath();
        viewId = viewId.substring(viewId.lastIndexOf("/") + 1);
        externalContext.redirect(String.format("%s/%s/%s", context, nextRenderMode.name().toLowerCase(), viewId));
    }
}
