import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

fun main() {
    val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
    val schema = schemaFactory.newSchema(
        StreamSource(
            """<?xml version="1.0" encoding="UTF-8"?>
        <xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema">
        
        <xsd:complexType name="infectedPerson">
            <xsd:sequence>
                <xsd:element name="ID" type="id"/>
                <xsd:element name="name" type="xsd:string"/>
                <xsd:element name="surname" type="xsd:string"/>
                <xsd:element name="birthDate" type="xsd:date"/>
                <xsd:element name="gender" type="gender"/>
                <xsd:element name="nationality" type="xsd:string"/>
                <xsd:element name="address" type="xsd:string"/>
                <xsd:element name="education" type="xsd:string"/>
                <xsd:element name="description" type="xsd:string"/>
                <xsd:element name="lvlOfInfection" type="infectionLvl"/>
                <xsd:element name="photos" type="photo" maxOccurs="unbounded"/>
            </xsd:sequence>
        </xsd:complexType>
        
        <xsd:element name="infectedPersons">
            <xsd:complexType>
                <xsd:sequence>
                    <xsd:element name="infectedPerson" type="infectedPerson" minOccurs="1" maxOccurs="1000"/>
                </xsd:sequence>
            </xsd:complexType>
        </xsd:element>
        
        
        <xsd:simpleType name="id">
            <xsd:restriction base="xsd:string">
                <xsd:pattern value="[0-9]{11}"/>
            </xsd:restriction>
        </xsd:simpleType>
        
        
        <xsd:simpleType name="infectionLvl">
            <xsd:restriction base="xsd:integer">
                <xsd:minInclusive value="1"/>
                <xsd:maxInclusive value="10"/>
            </xsd:restriction>
        </xsd:simpleType>
        
        <xsd:simpleType name="photo">
            <xsd:restriction base="xsd:string">
                <xsd:pattern value="/[a-zA-Z0-9]+.[a-z]{3,4}"/>
            </xsd:restriction> 
        </xsd:simpleType>
        
        <xsd:simpleType name="gender">
            <xsd:restriction base="xsd:string">
                <xsd:enumeration value="male"/>
                <xsd:enumeration value="female"/>
            </xsd:restriction>
        </xsd:simpleType>
        
        </xsd:schema>""".trimIndent().byteInputStream()
        )
    )

    val validator = schema.newValidator()
    validator.validate(
        StreamSource(
            """
        <?xml version="1.0" encoding="UTF-8"?>
                
            <infectedPersons>
                <infectedPerson>
                  <ID>12345678901</ID>
                  <name>John</name>
                  <surname>Doe</surname>
                  <birthDate>1990-01-01</birthDate>
                  <gender>male</gender>
                  <nationality>US</nationality>
                  <address>123 Main St</address>
                  <education>College</education>
                  <description>Test description</description>
                  <lvlOfInfection>5</lvlOfInfection>
                  <photos>/photo.jpg</photos>
                  <photos>/photo1.jpg</photos>
                </infectedPerson>
                <infectedPerson>
                  <ID>12345678901</ID>
                  <name>Jan</name>
                  <surname>Valiser</surname>
                  <birthDate>0001-01-01</birthDate>
                  <gender>male</gender>
                  <nationality>SI</nationality>
                  <address>Crnci 24u</address>
                  <education>Feri</education>
                  <description>Opis:1</description>
                  <lvlOfInfection>10</lvlOfInfection>
                  <photos>/photo.jpg</photos>
                  <photos>/photo1.jpg</photos>
                </infectedPerson>
                <infectedPerson>
                  <ID>21312312321</ID>
                  <name>Aljosa</name>
                  <surname>Golob</surname>
                  <birthDate>2003-05-16</birthDate>
                  <gender>female</gender>
                  <nationality>SI</nationality>
                  <address>Koper 1</address>
                  <education>Feri</education>
                  <description>Opis:2</description>
                  <lvlOfInfection>3</lvlOfInfection>
                  <photos>/photo.jpg</photos>
                  <photos>/photo1.jpg</photos>
                </infectedPerson>
            </infectedPersons>
    
    """.trimIndent().byteInputStream()
        )
    )
}
