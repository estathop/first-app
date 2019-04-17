package com.askisi1;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;

@RestController
public class ImagesController {
	@PostMapping(path = "/test", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> save(@RequestBody Images imgs) throws Exception {
		Repository repo = new HTTPRepository("http://localhost:7200/repositories/ImageExerc");
		repo.init();

		ValueFactory f = repo.getValueFactory();

		try (RepositoryConnection con = repo.getConnection()) {
			// Object Property, Image->Tag
			IRI hasTag = f
					.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#hasTag");
			// Data Property of class Image
			IRI id = f.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#id");
			// Data Property of Class Image
			IRI source = f
					.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#source");
			// Data Property of Class Tag
			IRI tag = f.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#tag");
			// Class of Image
			IRI imageClass = f
					.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#Image");
			// Class of Tag
			IRI tagClass = f
					.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#Tag");

			for (int x = 0; x < imgs.getOuterlist().size(); x++) {

				int randomNum = ThreadLocalRandom.current().nextInt(0, 1000);
				String generatedString = Integer.toString(randomNum);

				int randomNum2 = ThreadLocalRandom.current().nextInt(0, 1000);
				String generatedString2 = Integer.toString(randomNum2);
				IRI imageInstance = f
						.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#"
								+ generatedString);
				IRI tagInstance = f
						.createIRI("http://www.semanticweb.org/evangelos/ontologies/2019/3/untitled-ontology-5#"
								+ generatedString2);
				Literal imageInstanceId = f.createLiteral(imgs.getOuterlist().get(x).getId());
				Literal imageInstanceSource = f.createLiteral(imgs.getOuterlist().get(x).getSource());

				// if ID already exists in repository skip loop
				boolean idincluded = con.hasStatement(null, id, imageInstanceId, false);
				if (idincluded) {
					continue;
				}
				// Instance of Image as subject
				con.add(imageInstance, hasTag, tagInstance);
				con.add(imageInstance, id, imageInstanceId);
				con.add(imageInstance, source, imageInstanceSource);
				con.add(imageInstance, RDF.TYPE, imageClass);
				con.add(imageInstance, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
				// Instance of Tag as subject
				con.add(tagInstance, RDF.TYPE, tagClass);
				con.add(tagInstance, RDF.TYPE, OWL.NAMEDINDIVIDUAL);

				for (int y = 0; y < imgs.getOuterlist().get(x).getTags().size(); y++) {
					Literal tagInstanceTag = f.createLiteral(imgs.getOuterlist().get(x).getTags().get(y));
					con.add(tagInstance, tag, tagInstanceTag);
				}
			}
		}
		return ResponseEntity.ok("ok");
	}
}