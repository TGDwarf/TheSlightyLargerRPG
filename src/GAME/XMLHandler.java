package GAME;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by dot on 02-03-2016.
 */
public class XMLHandler {

    /**
     * @param game the game object
     * Writes a lot of things to an XML file located in the game folder
     */
    public void XMLWriter(Game game) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // Root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("GAME_Info");
        doc.appendChild(rootElement);

        //region Player
        // Player element
        Element player = doc.createElement("Player_Info");
        rootElement.appendChild(player);

        // set id attribute on player element
        player.setAttribute("id", "1");

        // playerName element
        Element playerName = doc.createElement("Player_Name:");
        playerName.appendChild(doc.createTextNode("" + game.player.getName()));
        player.appendChild(playerName);

        // playerLevel element
        Element playerLevel = doc.createElement("Player_Level:");
        playerLevel.appendChild(doc.createTextNode("" + game.player.getLevel()));
        player.appendChild(playerLevel);

        // playerExperience element
        Element playerExperience = doc.createElement("Player_Experience:");
        playerExperience.appendChild(doc.createTextNode("" + game.player.getExperience()));
        player.appendChild(playerExperience);

        // playerHealth element
        Element playerHealth = doc.createElement("Player_Health:");
        playerHealth.appendChild(doc.createTextNode("" + game.player.getHealth()));
        player.appendChild(playerHealth);

        // listOfSkills element
        Element listOfSkills = doc.createElement("Skill_List:");
        player.appendChild(listOfSkills);

        //listOfSkills elements
        Element skill1 = doc.createElement("Hack");
        listOfSkills.appendChild(skill1);
        Element skill2 = doc.createElement("Spin");
        listOfSkills.appendChild(skill2);
        Element skill3 = doc.createElement("Cleave");
        listOfSkills.appendChild(skill3);
        //endregion

        //region Creature
        // Monster element
        Element creature = doc.createElement("Creature_Info");
        rootElement.appendChild(creature);

        // set id attribute on player element
        creature.setAttribute("id", "2");


        // creatureHealth element
        Element creatureHealth = doc.createElement("Creature_Max_Health:");
        creatureHealth.appendChild(doc.createTextNode("" + game.creature.getHealth_Max()));
        creature.appendChild(creatureHealth);

        // creatureName element
        Element creatureName = doc.createElement("Creature_Name:");
        creatureName.appendChild(doc.createTextNode("" + game.creature.getName()));
        creature.appendChild(creatureName);

        // creatureDescription element
        Element creatureDescription = doc.createElement("Creature_Description:");
        creatureDescription.appendChild(doc.createTextNode("" + game.creature.getDescription()));
        creature.appendChild(creatureDescription);
        //endregion

        //region Map
        // Game element
        Element Map = doc.createElement("Map_Info");
        rootElement.appendChild(Map);

        // set id attribute on player element
        Map.setAttribute("id", "3");


        // TurnsNumber element
        Element turnsNumber = doc.createElement("Number_of_turns_this_game:");
        turnsNumber.appendChild(doc.createTextNode("" + game.getTurnsInGame()));
        Map.appendChild(turnsNumber);

        // creaturesDefeated element
        Element creaturesDefeated = doc.createElement("Creatures_Defeated:");
        creaturesDefeated.appendChild(doc.createTextNode("" + game.getCreaturesDefeated()));
        Map.appendChild(creaturesDefeated);
        //endregion


        // Write contents to an xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("Game.xml"));

        // Use the following to print to the console
        // StreamResult result = new StreamResult(System.out);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("XML File saved!");
    }

    /**
     * Reads the info in the XML file location in the game folder and prints it to console
     */
    public void XMLReader() {

        //Create Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        //Build document
        Document document = null;
        try {
            document = builder.parse(new File("Game.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Normalize XML Structure
        document.getDocumentElement().normalize();

        //Get the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all elements of this name
        NodeList pList = document.getElementsByTagName("Player_Info");
        System.out.println("============================");

        //Currently only 1 Player element, could become more with the addition of multiplayer... HA! not likely anytime soon
        for (int temp = 0; temp < pList.getLength(); temp++)
        {
            Node node = pList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Print details for each element
                Element eElement = (Element) node;
                System.out.println("Player id : "  + eElement.getAttribute("id"));
                System.out.println("Player Name : "  + eElement.getElementsByTagName("Player_Name:").item(0).getTextContent());
                System.out.println("Player Level : "   + eElement.getElementsByTagName("Player_Level:").item(0).getTextContent());
                System.out.println("Player Experience : "    + eElement.getElementsByTagName("Player_Experience:").item(0).getTextContent());
            }
        }

        //Get all elements of this name
        NodeList cList = document.getElementsByTagName("Creature_Info");
        System.out.println("============================");

        //Currently only 1 type of creature element, at the moment it does not make sense to add more types
        for (int temp = 0; temp < cList.getLength(); temp++)
        {
            Node node = cList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Print details for each element
                Element eElement = (Element) node;
                //Again, this whole id business does not make sense until more creatures are written to the list
                System.out.println("Creature id : "  + eElement.getAttribute("id"));
                System.out.println("Creature Max Health : "  + eElement.getElementsByTagName("Creature_Max_Health:").item(0).getTextContent());
                System.out.println("Creature Name : "   + eElement.getElementsByTagName("Creature_Name:").item(0).getTextContent());
                System.out.println("Creature Description : "    + eElement.getElementsByTagName("Creature_Description:").item(0).getTextContent());
            }
        }

        //Get all elements of this name
        NodeList mList = document.getElementsByTagName("Map_Info");
        System.out.println("============================");

        //Currently only 1 type of creature element, at the moment it does not make sense to add more types
        for (int temp = 0; temp < mList.getLength(); temp++)
        {
            Node node = mList.item(temp);
            System.out.println("");    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Print details for each element
                Element eElement = (Element) node;
                //Again, this whole id business does not make sense
                System.out.println("Map id : "  + eElement.getAttribute("id"));
                System.out.println("Number of turns this game : "  + eElement.getElementsByTagName("Number_of_turns_this_game:").item(0).getTextContent());
                System.out.println("Creatures defeated : "   + eElement.getElementsByTagName("Creatures_Defeated:").item(0).getTextContent());
            }
        }
    }
}
