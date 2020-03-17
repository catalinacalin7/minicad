// clasa necesara pentru Factory pattern
public final class ShapeFactory {
    // tot aici am folosit si Singleton
    private static ShapeFactory instance = new ShapeFactory();

    //constructorul pentru singleton trebuie sa fie private
    private ShapeFactory() {
    }

    // metoda ce returneaza singura instanta
    public static ShapeFactory getInstance() {
        return instance;
        }

    // metoda ce returneaza o figura noua, tipul careia este in dependenta
    // cu stringul primit ca paramentru(citit din fis. de intrare)
    // toate figurile se afla intr-o ierarhie unde parintele este
    // clasa Visitable, deci vom returna un obiect de tip Visitable
    public Visitable getShape(final String shapeType) {
        if (shapeType == null) {
            return null;
            }
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();

            } else if (shapeType.equalsIgnoreCase("SQUARE")) {
                return new Square();

                } else if (shapeType.equalsIgnoreCase("DIAMOND")) {
                    return new Diamond();

                    } else if (shapeType.equalsIgnoreCase("CIRCLE")) {
                        return new Circle();

                        } else if (shapeType.equalsIgnoreCase("LINE")) {
                            return new Line();

                            } else if (shapeType.equalsIgnoreCase("POLYGON")) {
                                return new Polygon();

                                } else if (shapeType.equalsIgnoreCase("TRIANGLE")) {
                                    return new Triangle();
                                    }
        return null;
        }
    }


