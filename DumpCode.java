package com.askisi1;
/**
 * import org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import static org.junit.Assert.*; import static
 * org.semanticweb.owlapi.model.parameters.Imports.INCLUDED; import static
 * org.semanticweb.owlapi.search.Searcher.*; import static
 * org.semanticweb.owlapi.vocab.OWLFacet.*;
 * 
 * import java.io.ByteArrayOutputStream; import java.io.File; import
 * java.util.*;
 * 
 * import javax.annotation.Nonnull;
 * 
 * import org.junit.Ignore; import org.junit.Test; //import
 * org.semanticweb.owlapi.api.test.baseclasses.*; import
 * org.semanticweb.owlapi.apibinding.OWLManager; import
 * org.semanticweb.owlapi.formats.ManchesterSyntaxDocumentFormat; import
 * org.semanticweb.owlapi.formats.OWLXMLDocumentFormat; import
 * org.semanticweb.owlapi.formats.TurtleDocumentFormat; import
 * org.semanticweb.owlapi.io.StreamDocumentTarget; import
 * org.semanticweb.owlapi.io.StringDocumentSource; import
 * org.semanticweb.owlapi.io.StringDocumentTarget; import
 * org.semanticweb.owlapi.model.*; import org.semanticweb.owlapi.reasoner.*;
 * import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner; import
 * org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory; import
 * org.semanticweb.owlapi.search.Filters; import org.semanticweb.owlapi.util.*;
 * import org.semanticweb.owlapi.vocab.OWL2Datatype; import
 * org.semanticweb.owlapi.vocab.OWLFacet; import
 * org.semanticweb.owlapi.vocab.OWLRDFVocabulary; import
 * com.google.common.base.Optional;
 * 
 * import uk.ac.manchester.cs.owlapi.modularity.ModuleType; import
 * uk.ac.manchester.cs.owlapi.modularity.SyntacticLocalityModuleExtractor;
 * import java.nio.charset.Charset;
 * 
 * @RestController public class ImagesController {
 * @PostMapping(path = "/test",consumes = {MediaType.APPLICATION_JSON_VALUE} )
 *                   public ResponseEntity<String> save(@RequestBody Images
 *                   imgs) throws Exception{ //Image[] localarray = new Image[]
 *                   {imgs.images.getOuterlist()}; //System.out.print
 *                   (imgs.images.getouterlist()); String test23 = null; File
 *                   file = new File("/home/evangelos/Desktop/ontoaskisi.owl");{
 *                   try { OWLOntologyManager manager =
 *                   OWLManager.createOWLOntologyManager(); OWLOntology
 *                   localOntology =
 *                   manager.loadOntologyFromOntologyDocument(file); String
 *                   ontoIRI =
 *                   localOntology.getOntologyID().getOntologyIRI().get().toString();
 *                   int qwe = localOntology.getAxiomCount(); test23 =
 *                   imgs.getOuterlist().get(1).getTags().get(0); //List<Image>
 *                   aaa = imgs.images.getOuterlist(); //test23 =
 *                   Integer.toString(aaa.size()); //qwe = aaa.get(0).getId();
 *                   //test23 = Integer.toString(qwe); /**OWLDataFactory factory
 *                   = manager.getOWLDataFactory(); for (int
 *                   x1=0;x1<imgs.images.getOuterlist().length;x1++) { qwe = 45;
 *                   test23 = Integer.toString(qwe);
 * 
 *                   byte[] array = new byte[7]; // length is bounded by 7 new
 *                   Random().nextBytes(array); String generatedString = new
 *                   String(array, Charset.forName("UTF-8")); ArrayList<String>
 *                   tagtag = localarray[x1].getTags();
 * 
 *                   OWLNamedIndividual imagex =
 *                   factory.getOWLNamedIndividual(IRI.create(ontoIRI+"#"+generatedString));
 *                   OWLObjectProperty hasTag =
 *                   factory.getOWLObjectProperty(ontoIRI+"#hasTag");
 * 
 *                   OWLClass imageClass =
 *                   factory.getOWLClass(IRI.create(ontoIRI + "#Image"));
 *                   OWLClass tagClass = factory.getOWLClass(IRI.create(ontoIRI
 *                   + "#Tag"));
 * 
 *                   OWLClassAssertionAxiom ax =
 *                   factory.getOWLClassAssertionAxiom(imageClass, imagex);
 * 
 *                   manager.addAxiom(localOntology, ax);
 * 
 *                   for(int y1=0;y1<localarray[y1].getTags().size();y1++) {
 *                   OWLNamedIndividual tagx =
 *                   factory.getOWLNamedIndividual(IRI.create(ontoIRI+"#"+tagtag.get(y1).toString()));
 *                   OWLClassAssertionAxiom ax1 =
 *                   factory.getOWLClassAssertionAxiom(tagClass, tagx);
 *                   manager.addAxiom(localOntology, ax1);
 * 
 *                   OWLObjectPropertyAssertionAxiom propertyAssertion = factory
 *                   .getOWLObjectPropertyAssertionAxiom(hasTag, imagex, tagx);
 *                   manager.addAxiom(localOntology, propertyAssertion); };
 * 
 *                   OWLDataProperty hasID =
 *                   factory.getOWLDataProperty(IRI.create(ontoIRI+"#id"));
 *                   OWLDatatype positiveintegerDatatype =
 *                   factory.getOWLDatatype(OWL2Datatype.XSD_NON_NEGATIVE_INTEGER);
 *                   OWLLiteral literal =
 *                   factory.getOWLLiteral(String.valueOf(localarray[x1].getId()),
 *                   positiveintegerDatatype); OWLAxiom axid =
 *                   factory.getOWLDataPropertyAssertionAxiom(hasID, imagex,
 *                   literal); manager.addAxiom(localOntology, axid);
 * 
 *                   OWLDataProperty hasSource =
 *                   factory.getOWLDataProperty(IRI.create(ontoIRI+"#source"));
 *                   OWLDataPropertyAssertionAxiom dataPropertyAssertion2 =
 *                   factory .getOWLDataPropertyAssertionAxiom(hasSource,
 *                   imagex, localarray[x1].getSource());
 *                   manager.addAxiom(localOntology, dataPropertyAssertion2); };
 * 
 *                   //qwe = localOntology.getAxiomCount(); //test23 =
 *                   Integer.toString(qwe); File file2 = new
 *                   File("/home/evangelos/Desktop/ontoaskisi23.owl"); try {
 *                   manager.saveOntology(localOntology,
 *                   IRI.create(file2.toURI())); //System.out.println("Data
 *                   assertion was successful!"); } catch
 *                   (OWLOntologyStorageException e) {e.printStackTrace();}/** }
 *                   catch (OWLOntologyCreationException e) {
 *                   e.printStackTrace(); }
 * 
 *                   } return ResponseEntity.ok(test23); } }
 **/