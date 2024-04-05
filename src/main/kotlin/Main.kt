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
                <xsd:element name="gender" type="xsd:boolean"/>
                <xsd:element name="nationality" type="xsd:string"/>
                <xsd:element name="address" type="xsd:string"/>
                <xsd:element name="education" type="xsd:string"/>
                <xsd:element name="description" type="xsd:string"/>
                <xsd:element name="lvlOfInfection" type="xsd:infectionLvl"/>
                <xsd:element name="photos" type="xsd:photo" maxOccurs="unbounded"/>
            </xsd:sequence>
        </xsd:complexType>
        
        <xsd:element name="infectedPersons">
            <xsd:complexType>
                <xsd:sequence>
                    <xsd:element name="infectedPerson" type="infectedPerson" minOccurs="1" maxOccurs="1000"/>
                </xsd:sequence>
            </xsd:complexType>
        
        
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
                <xsd:pattern value="[a-zA-Z0-9]+.[a-z]{3,4}"/>
            </xsd:restriction> 
        </xsd:simpleType>
        
        </xsd:schema>""".trimIndent().byteInputStream()
        )
    )

    val validator = schema.newValidator()
    validator.validate(StreamSource("""<?xml version="1.0" encoding="UTF-8"?>
        <root>
            <child>text</child>
        </root>""".trimIndent().byteInputStream()))
}
