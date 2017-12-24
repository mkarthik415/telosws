package telosws.views;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;

/**
 * Created by karthikmarupeddi on 8/23/15.
 */
@SpringUI
public class MyVaadinUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(link);
    }


    Link link = new Link("Take me a away to a faraway land",
            new ExternalResource("http://vaadin.com/"));
}