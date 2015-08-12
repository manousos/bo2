## Description ##
The documents module defines a contract for a platform agnostic java template engine that can be used to create business documents.

The contract is declared in the Bo2UtilsDoc module. Its main abstractions are:
  * The [document](http://bo2.googlecode.com/svn/trunk/docs/javadocs/Bo2UtilsDoc/gr/interamerican/bo2/utils/doc/BusinessDocument.html)
  * The [table](http://bo2.googlecode.com/svn/trunk/docs/javadocs/Bo2UtilsDoc/gr/interamerican/bo2/utils/doc/DocumentTable.html)
  * The [engine](http://bo2.googlecode.com/svn/trunk/docs/javadocs/Bo2UtilsDoc/gr/interamerican/bo2/utils/doc/DocumentEngine.html)


The idea is:

  1. A document designer creates a document template. The template contains all the formatting of the document.
  1. The document designer in cooperation with a developer fills the template with expressions that can be evaluated against a model object.
  1. The document creation flow is analysed and developed. Features like filling tables, manipulating tables, combining multiple templates for the creation of one document are defined in the analysis.

This approach aims to separate document presentation from the logic that creates it. Document presentation information (the fields and the formatting) are stored in the document template. Creation logic exists in java code that creates the document.

## Implementations ##
At the moment only Bo2OdfToolkit provides implementations of the above mentioned abstractions. The document engine is based on [Open Document Format](https://www.oasis-open.org/committees/tc_home.php?wg_abbrev=office) and the [OdfToolkit](http://incubator.apache.org/odftoolkit/) project.

Different implementations could be developed for different engines for example, [MS Office](http://en.wikipedia.org/wiki/Office_Open_XML) and [Apache POI](http://poi.apache.org/) or PDF and [iText](http://itextpdf.com/).






## Libraries ##
  * Bo2UtilsDoc
  * Bo2OdfToolkit

## Dependencies ##
#### Bo2 libraries ####
  * Bo2Utils
  * Bo2Creation
#### Third party libraries ####
  * SLF4J
  * Apache commons beans
  * Javassist