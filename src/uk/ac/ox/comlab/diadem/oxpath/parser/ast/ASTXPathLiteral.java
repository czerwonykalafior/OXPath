/* Generated By:JJTree: Do not edit this line. ASTXPathLiteral.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package uk.ac.ox.comlab.diadem.oxpath.parser.ast;

import uk.ac.ox.comlab.diadem.oxpath.parser.*;

public
class ASTXPathLiteral extends SimpleNode {
  public ASTXPathLiteral(int id) {
    super(id);
  }

  public ASTXPathLiteral(OXPathParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(OXPathParserVisitor visitor, Object data) throws uk.ac.ox.comlab.diadem.oxpath.utils.OXPathException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=dcd40fc5a793fd5245cd345e6b0ee1b8 (do not edit this line) */
