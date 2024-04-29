# Multi-dbconnection
Multi-DB Connection Framework provides a robust and flexible Java library designed for applications that require connectivity to multiple types of databases. It abstracts the complexities involved in establishing and managing connections, making it easier to integrate with various database systems.

## Getting started:
1. Clone the repository.
2. Add the library to your Java project.
3. Use the ConnectionFactory to establish a connection to your desired database.
4. Implement the JdbcRowMapper to map your database tables to Java objects.
5. Use the SQL helpers to interact with your database efficiently.

## Example usage:
```
// Building a connection for PostgreSQL
Connection conn = ConnectionFactory.engine(SupportedEngine.POSTGRESQL)
                                   .host("localhost")
                                   .port(5432)
                                   .user("yourUsername")
                                   .password("yourPassword")
                                   .schema("yourSchema")
                                   .build();

// Implement custom row mapper
public class ClienteRowMapper extends JdbcRowMapper<Cliente> {
    public static final String TABLE_NAME = "cliente";

    public enum Fields implements JdbcRowMapper.FieldsEnum {
        ID(true, true),
        NIF,
        NOMBRE,
        APELLIDO1,
        APELLIDO2,
        DIRECCION,
        TELEFONO,
        EMAIL;

        final private boolean primaryKey;
        final private boolean autoIncrement;

        private Fields() {
            this.primaryKey = false;
            this.autoIncrement = false;
        }

        private Fields(boolean primaryKey, boolean autoIncrement) {
            this.primaryKey = primaryKey;
            this.autoIncrement = autoIncrement;
        }

        @Override
        public boolean isPrimaryKey() {
            return primaryKey;
        }

        @Override
        public boolean isAutoIncremented() {
            return autoIncrement;
        }
    }

    ClienteRowMapper() {
        super();
        this.setFields(Fields.values());
        this.setTableName(TABLE_NAME);
    }

    @Override
    public Cliente mapRow(ResultSet rs, int i) throws SQLException {
        return new Cliente(
                rs.getString(Fields.NIF.toString()),
                rs.getString(Fields.NOMBRE.toString()),
                rs.getString(Fields.APELLIDO1.toString()),
                rs.getString(Fields.APELLIDO2.toString()),
                rs.getString(Fields.DIRECCION.toString()),
                rs.getInt(Fields.TELEFONO.toString()),
                rs.getString(Fields.EMAIL.toString())
        );
    }

// Interact with the database
public class ClientDAO {
    private final ConnectionFactory.ConnectionFactoryBuilder connectionBuilder;
    private final SQLRepositoryHelper<Client> helper;

    public ClientDAO(SupportedEngine engine, String host, int port, String user, String password, String database) {
        this.connectionBuilder = ConnectionFactory.engine(engine).host(host).port(port).user(user).password(password).schema(database);
        this.helper = new JdbcCrudRepository<>(new ClientRowMapper());
    }

    public void getAllClients() throws SQLException {
        try (Connection connection = connectionBuilder.build()){
            String sql = helper.generateSelectAll();
        }
    }

    public void insertClient(Client cliente) throws SQLException {
        try (Connection connection = connectionBuilder.build()){
            String sql = helper.generateInsert();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, cliente.getNif());
                preparedStatement.setString(2, cliente.getName());
                preparedStatement.setString(3, cliente.getLastName1());
                preparedStatement.setString(4, cliente.getLastName2());
                preparedStatement.setString(5, cliente.getAddress());
                preparedStatement.setInt(6, cliente.getTelephone());
                preparedStatement.setString(7, cliente.getEmail());
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Se ha insertado el cliente correctamente");
                }
            }
        }
    }
}
```
## Contribute:
Contributions are welcome! Feel free to fork the project, open issues, and submit pull requests.

