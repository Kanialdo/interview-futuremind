package pl.krystiankaniowski.futuremind.model;

import org.junit.Assert;
import org.junit.Test;

import pl.krystiankaniowski.futuremind.model.database.Row;
import pl.krystiankaniowski.futuremind.model.rest.SingleData;

public class ModelConverterTest {

    @Test
    public void convert() throws Exception {

        SingleData data = new SingleData();

        data.setTitle("thrombopenia");
        data.setDescription("Ball tip ham swine pork fatback pig brisket alcatra pork chop pastrami meatloaf tail sirloin.  Bresaola pork chop porchetta venison.  Drumstick short ribs tail ham jowl doner andouille.  Drumstick beef ribs swine capicola jerky.  Turducken tri-tip landjaeger chicken leberkas.\\n\\nBall tip swine short ribs pork biltong.  Strip steak boudin turkey short loin swine.  Shankle andouille jerky porchetta shank cow ball tip ribeye pork chop pig frankfurter brisket boudin chicken cupim.  Shankle salami porchetta pork belly short ribs, tri-tip rump chicken bresaola cow venison tenderloin.  Pork belly alcatra beef bacon.    http://www.google.de/?gfe_rd=cr&ei=4v-kV4-qJqOh8weaw7SYCw");
        data.setOrderId(2);
        data.setModificationDate("2013-01-15");
        data.setImageUrl("http://lorempixel.com/120/120/abstract/");

        Row row = ModelConverter.Companion.toDatabaseModel(data);

        Assert.assertEquals(row.getDescription(), "Ball tip ham swine pork fatback pig brisket alcatra pork chop pastrami meatloaf tail sirloin.  Bresaola pork chop porchetta venison.  Drumstick short ribs tail ham jowl doner andouille.  Drumstick beef ribs swine capicola jerky.  Turducken tri-tip landjaeger chicken leberkas.\\n\\nBall tip swine short ribs pork biltong.  Strip steak boudin turkey short loin swine.  Shankle andouille jerky porchetta shank cow ball tip ribeye pork chop pig frankfurter brisket boudin chicken cupim.  Shankle salami porchetta pork belly short ribs, tri-tip rump chicken bresaola cow venison tenderloin.  Pork belly alcatra beef bacon.");
        Assert.assertEquals(row.getUrl(), "http://www.google.de/?gfe_rd=cr&ei=4v-kV4-qJqOh8weaw7SYCw");

    }

}
