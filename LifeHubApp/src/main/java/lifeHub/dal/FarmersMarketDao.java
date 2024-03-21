package lifeHub.dal;

import java.sql.*;
import lifeHub.model.FarmersMarket;
import lifeHub.model.FarmersMarket.MarketType;

public class FarmersMarketDao {
    protected ConnectionManager connectionManager;

    private static FarmersMarketDao instance = null;
    protected FarmersMarketDao() {
        connectionManager = new ConnectionManager();
    }
    public static FarmersMarketDao getInstance() {
        if(instance == null) {
            instance = new FarmersMarketDao();
        }
        return instance;
    }

    //CREATE
    public FarmersMarket create(FarmersMarket market) throws SQLException {
        String insertMarket = "INSERT INTO FarmersMarket(NeighborZipId, Name, Dates, Hours, Website, MarketType) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertMarket, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            insertStmt.setInt(1, market.getNeighborZipId());
            insertStmt.setString(2, market.getName());
            insertStmt.setString(3, market.getDates());
            insertStmt.setString(4, market.getHours());
            insertStmt.setString(5, market.getWebsite());
            insertStmt.setString(6, market.getMarketType().toString());
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int marketId = -1;
            if(resultKey.next()) {
                marketId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            market.setMarketId(marketId);
            return market;
        } finally {
            if(resultKey != null) resultKey.close();
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    //READ
    public FarmersMarket getMarketById(int marketId) throws SQLException {
        String selectMarket = "SELECT MarketId, NeighborZipId, Name, Dates, Hours, Website, MarketType FROM FarmersMarket WHERE MarketId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMarket);
            selectStmt.setInt(1, marketId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int neighborZipId = results.getInt("NeighborZipId");
                String name = results.getString("Name");
                String dates = results.getString("Dates");
                String hours = results.getString("Hours");
                String website = results.getString("Website");
                MarketType marketType = MarketType.valueOf(results.getString("MarketType"));

                FarmersMarket market = new FarmersMarket(marketId, neighborZipId, name, dates, hours, website, marketType);
                return market;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    // UPDATE
    public FarmersMarket update(FarmersMarket market) throws SQLException {
        String updateMarket = "UPDATE FarmersMarket SET NeighborZipId=?, Name=?, Dates=?, Hours=?, Website=?, MarketType=? WHERE MarketId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateMarket);
            // Set parameters for the update
            updateStmt.setInt(1, market.getNeighborZipId());
            updateStmt.setString(2, market.getName());
            updateStmt.setString(3, market.getDates());
            updateStmt.setString(4, market.getHours());
            updateStmt.setString(5, market.getWebsite());
            updateStmt.setString(6, market.getMarketType().toString());
            updateStmt.setInt(7, market.getMarketId());

            updateStmt.executeUpdate();

            // Return the updated market
            return market;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    // DELETE
    public FarmersMarket delete(FarmersMarket market) throws SQLException {
        String deleteMarket = "DELETE FROM FarmersMarket WHERE MarketId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteMarket);
            deleteStmt.setInt(1, market.getMarketId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
