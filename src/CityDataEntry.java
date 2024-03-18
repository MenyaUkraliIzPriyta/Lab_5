import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CityDataEntry {
    public static void main(String[] args) {
        try {
            // Загружаем XML файл в память в виде DOM дерева
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("city_collection.xml"));

            // Создаем новый элемент для нового объекта City
            Element cityElement = document.createElement("city");

            // Создаем элементы для нового объекта City и добавляем их в новый элемент
            Element nameElement = document.createElement("name");
            nameElement.appendChild(document.createTextNode("Новый город"));
            cityElement.appendChild(nameElement);

            // Добавьте другие элементы для нового объекта City

            // Находим корневой элемент "cities" в DOM дереве
            Element rootElement = document.getDocumentElement();

            // Добавляем новый элемент в корневой элемент
            rootElement.appendChild(cityElement);

            // Сохраняем измененное DOM дерево обратно в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("cities.xml"));
            transformer.transform(source, result);

            System.out.println("Новый объект успешно добавлен в XML файл.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
